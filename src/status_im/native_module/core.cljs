(ns status-im.native-module.core
  (:require [status-im.native-module.impl.module :as native-module]))

(def adjust-resize 16)

(defn start-node [config]
  (native-module/start-node config))

(defn node-ready []
  (native-module/node-ready))

(defn stop-node []
  (native-module/stop-node))

(defn create-account [password callback]
  (native-module/create-account password callback))

(defn recover-account [passphrase password callback]
  (native-module/recover-account passphrase password callback))

(defn login [address password callback]
  (native-module/login address password callback))

(defn verify [address password callback]
  (native-module/verify address password callback))

(defn set-soft-input-mode [mode]
  (native-module/set-soft-input-mode mode))

(defn clear-web-data []
  (native-module/clear-web-data))

(defn call-rpc [payload callback]
  (native-module/call-rpc payload callback))

(defn call-private-rpc [payload callback]
  (native-module/call-private-rpc payload callback))

(defn sign-message [rpcParams callback]
  (native-module/sign-message rpcParams callback))

(defn send-transaction [rpcParams password callback]
  (native-module/send-transaction rpcParams password callback))

(defn module-initialized! []
  (native-module/module-initialized!))

(defn notify-users [m callback]
  (native-module/notify-users m callback))

(defn send-logs [dbJson]
  (native-module/send-logs dbJson))

(defn add-peer [enode callback]
  (native-module/add-peer enode callback))

(defn close-application []
  (native-module/close-application))

(defn connection-change [data]
  (native-module/connection-change data))

(defn app-state-change [state]
  (native-module/app-state-change state))

(defn get-device-UUID [callback]
  (native-module/get-device-UUID callback))

(defn is24Hour []
  (native-module/is24Hour))

(def extract-group-membership-signatures native-module/extract-group-membership-signatures)

(def sign-group-membership native-module/sign-group-membership)

(def enable-installation native-module/enable-installation)

(def disable-installation native-module/disable-installation)

(def update-mailservers native-module/update-mailservers)
