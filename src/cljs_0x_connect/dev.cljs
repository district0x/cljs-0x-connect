(ns cljs-0x-connect.dev
  (:require [cljs-0x-connect.ws-orderbook :as ws-orderbook]
            [cljs-0x-connect.http-client :as http-client]))

(def fees-request {:exchange-contract-address "0x12459c951127e0c374ff9105dda097662a027093"
                   :maker "0x9e56625509c2f60af937f23b7b532600390e8c8b"
                   :taker "0x0000000000000000000000000000000000000000"
                   :maker-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                   :taker-token-address "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"
                   :maker-token-amount "10000000000000000"
                   :taker-token-amount "20000000000000000"
                   :expiration-unix-timestamp-sec "42"
                   :salt "67006738228878699843088602623665307406148487219438534730168799356281242528500"})

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
        handler (ws-orderbook/create-channel-handler
                 {:on-snapshot (fn [chan opts resp] (prn "@onSnapshot" "bids:" (count (aget resp "bids"))
                                                         "asks:" (count (aget resp "asks"))))
                  :on-update (fn [chan opts order] (prn "@onUpdate" "new order:" order))
                  :on-error (fn [chan opts resp] (prn "@onError" resp))
                  :on-close (fn [chan opts] (prn "@onClose"))})]

    #_(js-invoke (http-client/get-fees-async client {:request fees-request}) "then"
               (fn [response]
                 (prn response)))

    #_(ws-orderbook/subscribe chan {:opts orderbook-subscription-opts
                                    :handler handler})

    ))

(defn stop []
  (js/console.log "stop"))

(defn ^:export init []
  (enable-console-print!)
  (js/console.log "init")
  (start))



(def handler (ws-orderbook/create-channel-handler {:on-snapshot (fn [chan opts resp] (prn "bids:" (count (aget resp "bids"))
                                                                                          "asks:" (count (aget resp "asks"))))
                                                   :on-update (fn [chan opts order] (prn "new order:" order))
                                                   :on-error (fn [chan opts resp] (prn "Error:" resp))
                                                   :on-close (fn [chan opts] (prn "closing"))}))
