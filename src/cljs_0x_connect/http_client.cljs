(ns cljs-0x-connect.http-client
  (:require [camel-snake-kebab.core]
            [camel-snake-kebab.extras]
            [cljsjs.zeroxproject-connect])
  (:require-macros [cljs-0x-connect.macros :as macros]))

(def ^{:doc "Instance of HttpClient object"} *http-client-instance* (atom nil))

(defn create-http-client [url]
  (let [instance (new (aget js/connect "HttpClient") url)]
    (reset! *http-client-instance* instance)
    instance))

(macros/defsignatures [["getTokenPairsAsync" [client request & [opts]]]
                       ["getOrdersAsync" [client request & [opts]]]
                       ["getOrderAsync" [client request & [opts]]]
                       ["getOrderbookAsync" [client request & [opts]]]
                       ["getFeesAsync" [client request]]
                       ["submitOrderAsync" [client request & [opts]]]])
