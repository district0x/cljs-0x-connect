(ns cljs-0x-connect.dev
  (:require [cljs-0x-connect.ws-orderbook :as ws-orderbook]
            [cljs-0x-connect.http-client :as http-client]
            ["websocket" :as websocket]))

(def url "wss://api.radarrelay.com/0x/v0/ws")

(def config {:heartbeat-interval-ms 1000})

(def orderbook-subscription-opts {:base-token-address "0x2956356cd2a2bf3202f771f50d3d14a367b48070"
                                  :quote-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                                  :limit 20
                                  :snapshot true})

(defn start []
  (js/console.log "start")
  (let [client (http-client/create-http-client "https://api.radarrelay.com/0x/v0/")
        chan nil #_(ws-orderbook/create-orderbook-channel url config)
        handler nil #_(ws-orderbook/create-channel-handler
                                            {:on-snapshot (fn [chan opts resp] (prn "@onSnapshot" "bids:" (count (aget resp "bids"))
                                                                                    "asks:" (count (aget resp "asks"))))
                                             :on-update (fn [chan opts order] (prn "@onUpdate" "new order:" order))
                                             :on-error (fn [chan opts resp] (prn "@onError" resp))
                                             :on-close (fn [chan opts] (prn "@onClose"))})]

    #_(js-invoke (http-client/get-order-async client {:request "0x2c66066520f33adeeb2dffb23c68287261fce778bff7ed8e22ea614fa202fd96"}) "then"
                 (fn [response]
                   (prn response)))

    ;; is 10000
    #_(prn (aget chan "_heartbeatIntervalMs"))



    #_(ws-orderbook/subscribe chan {:opts orderbook-subscription-opts
                                  :handler handler})



    ))

(defn stop []
  (js/console.log "stop"))

(defn ^:export init []
  (js/console.log "init")
  (start))
