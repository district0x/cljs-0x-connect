(ns cljs-0x-connect.ws-orderbook
  (:require ["websocket" :as websocket]
            ["@0xproject/connect" :refer (OrderbookChannel WebSocketOrderbookChannel)]))

(def ^{:doc "Instance of WebSocketOrderbookChannel object"} *orderbook-channel-instance* (atom nil))

(defn create-orderbook-channel [url config]

  (prn websocket)

  ;;(new WebSocketOrderbookChannel url)

  #_(let [instance (new WebSocketOrderbookChannel url)]
    (reset! *orderbook-channel-instance* instance)
    instance))
