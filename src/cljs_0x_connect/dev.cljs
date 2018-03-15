(ns cljs-0x-connect.dev
  (:require [cljs-0x-connect.http-client :as http-client]
            [cljs-0x-connect.ws-orderbook :as ws-orderbook]))

(def fees-request {:exchange-contract-address "0x12459c951127e0c374ff9105dda097662a027093"
                   :maker "0x9e56625509c2f60af937f23b7b532600390e8c8b"
                   :taker "0x0000000000000000000000000000000000000000"
                   :maker-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                   :taker-token-address "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"
                   :maker-token-amount "10000000000000000"
                   :taker-token-amount "20000000000000000"
                   :expiration-unix-timestamp-sec "42"
                   :salt "67006738228878699843088602623665307406148487219438534730168799356281242528500"})

(def orderbook-request {:base-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                        :quote-token-address "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"})

(def opts {:page 1
           :perPage 2})

(defn start []
  (js/console.log "start")
  (let [channel (ws-orderbook/create-orderbook-channel "wss://ws.radarrelay.com/0x/v0/ws" {:heartbeat-interval-ms 10000})]



    ))


(defn stop []
  (js/console.log "stop"))

(defn ^:export init []
  (js/console.log "init")
  (start))
