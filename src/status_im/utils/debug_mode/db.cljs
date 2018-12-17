(ns status-im.log-level.db
  (:require [cljs.spec.alpha :as spec]))

(spec/def :debug-mode/enabled-timestamp (spec/nilable int?))
(spec/def :debug-mode/prev-statusgo-log-level (spec/nilable string?))

(spec/def :debug-mode/debug-mode
  (spec/keys :req-un [:debug-mode/enabled-timestamp]
             :opt-un [:debug-mode/prev-log-level
                      :debug-mode/prev-statusgo-log-level]))
