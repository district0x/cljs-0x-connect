(ns cljs-0x-connect.ws-orderbook
  (:require [camel-snake-kebab.core :as camel-snake]
            [camel-snake-kebab.extras :as camel-snake-extras]
            [cljsjs.zeroxproject-connect]))

(def ^{:doc "Instance of WebSocketOrderbookChannel object"} *orderbook-channel-instance* (atom nil))

(defn create-orderbook-channel [url & [config]]
  ;; TODO: in browser?
  #_(aset WebSocketOrderbookChannel "_client" (aget websocket "w3cwebsocket"))
  (let [instance (new (aget js/connect "WebSocketOrderbookChannel") url (->> config
                                                                             (camel-snake-extras/transform-keys camel-snake/->camelCase)
                                                                             clj->js))]
    (reset! *orderbook-channel-instance* instance)
    instance))

(defn create-channel-handler [{:keys [:on-snapshot :on-update :on-error :on-close]}]
  (js-obj "onSnapshot" on-snapshot "onUpdate" on-update "onError" on-error "onClose" on-close))

(defn subscribe [chan {:keys [:opts :handler]}]
  (js-invoke chan "subscribe" (->> opts
                                   (camel-snake-extras/transform-keys camel-snake/->camelCase)
                                   clj->js) handler))

(defn close [chan]
  (js-invoke chan "close"))
