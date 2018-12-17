(ns status-im.utils.debug-mode.core
  (:require [clojure.string :as string]
            [re-frame.core :as re-frame]
            [taoensso.timbre :as log]
            [status-im.i18n :as i18n]
            [status-im.native-module.core :as status]
            [status-im.utils.config :as config]
            [status-im.utils.fx :as fx]
            [status-im.utils.types :as types]))

(fx/defn send-logs
  [{:keys [db] :as cofx}]
  ;; TODO: Add message explaining db export
  (let [db-json (types/clj->json (select-keys db [:discover-current-dapp
                                                  :discover-search-tags
                                                  :discoveries
                                                  :initial-props
                                                  :keyboard-height
                                                  :keyboard-max-height
                                                  :navigation-stack
                                                  :network
                                                  :network-status
                                                  :peers-count
                                                  :peers-summary]))]
    (status/send-logs db-json)))

(fx/defn enable-debug-mode
  [{:keys [db now] :as cofx} {:keys [value] :as log-level}]
  (let [settings (get-in db [:account/account :settings])]
    (if log-level
      (do (-> (string/lower-case value)
              keyword
              log/set-level!)
          (re-frame/dispatch [:log-level.ui/change-log-level-confirmed value])
          (fx/merge cofx
                    {:db (assoc db :debug-mode {:enabled-timestamp       now
                                                :prev-statusgo-log-level (:log-level settings)})}))
      (do (log/set-level! config/log-level)
          (re-frame/dispatch [:log-level.ui/change-log-level-confirmed (get-in db [:debug-mode :prev-statusgo-log-level])])
          (fx/merge cofx
                    {:db (dissoc db :debug-mode)})))))

(fx/defn prompt-enable-debug-mode
  [{:keys [db] :as cofx} {:keys [name] :as log-level}]
  (if log-level
    {:ui/show-confirmation {:title               (i18n/label :t/close-app-title)
                            :content             (i18n/label :t/change-log-level
                                                             {:log-level name})
                            :confirm-button-text (i18n/label :t/close-app-button)
                            :on-accept           #(re-frame/dispatch [:debug-mode/enable-debug-mode log-level])
                            :on-cancel           nil}}
    (re-frame/dispatch [:debug-mode/enable-debug-mode log-level])))

(defn debug-mode-enabled?
  [{:keys [now] :as cofx} {:keys [enabled-timestamp] :as debug-mode}]
  (and enabled-timestamp
       (< (- now enabled-timestamp) (* 20 60 1000))))
