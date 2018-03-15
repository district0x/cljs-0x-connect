(ns tests.app-test
  (:require [cljs.test :as test :refer [deftest is testing run-tests use-fixtures]]
            [cljs-0x-connect.http-client :as http-client]
            [cljs.core.async :as async :refer [<! >! put! timeout]])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]))

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

(deftest all-tests
  (let [client (http-client/create-http-client "https://api.radarrelay.com/0x/v0/")]

    (testing "get-fees-async"
      (test/async done
                  (js-invoke (http-client/get-fees-async client fees-request) "then"
                             (fn [response]
                               (is (= #{:fee-recipient :maker-fee :taker-fee} (into #{} (keys response))))
                               (done)))))

(testing "get-orderbook-async")

(test/async done
                  (js-invoke (http-client/get-orderbook-async client orderbook-request opts) "then"
                             (fn [response]
                               (is (= #{:bids :asks} (into #{} (keys response))))
                               (done))))


    ))
