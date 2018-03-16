(ns tests.app-test
  (:require [cljs.test :as test :refer [deftest is testing run-tests use-fixtures]]
            [cljs-0x-connect.http-client :as http-client]))

(def order-hash "0x2c66066520f33adeeb2dffb23c68287261fce778bff7ed8e22ea614fa202fd96")

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

(def token-pairs-request {:token-a "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359"
                          :token-b "0x01b3ec4aae1b8729529beb4965f27d008788b0eb"})

(def opts {:page 1
           :per-page 2})

(def single-order-keyset #{:salt :taker-fee :expiration-unix-timestamp-sec :taker-token-address :ec-signature :maker-token-address :exchange-contract-address :fee-recipient :maker-fee :taker-token-amount :maker :maker-token-amount :order-hash :taker})

(def client (http-client/create-http-client "https://api.radarrelay.com/0x/v0/"))

(deftest get-fees-test
  (test/async done
              (js-invoke (http-client/get-fees-async client {:request fees-request}) "then"
                         (fn [response]
                           (is (= #{:fee-recipient :maker-fee :taker-fee} (into #{} (keys response))))
                           (done)))))

(deftest get-orderbook-test
  (test/async done
              (js-invoke (http-client/get-orderbook-async client {:request orderbook-request :opts opts}) "then"
                         (fn [response]
                           (is (= #{:bids :asks} (into #{} (keys response))))
                           (done)))))

(deftest get-order-test
  (let [client (http-client/create-http-client "https://api.radarrelay.com/0x/v0/")]
    (test/async done
                (js-invoke (http-client/get-order-async client {:request order-hash :opts opts}) "then"
                           (fn [response]
                             (is (= (->> single-order-keyset
                                         (remove #{:order-hash})
                                         (into #{}))
                                    (into #{} (keys response))))
                             (done))))))

(deftest get-orders-test
  (test/async done
              (js-invoke (http-client/get-orders-async client) "then"
                         (fn [response]
                           (is (= single-order-keyset (into #{} (-> response first keys))))
                           (done)))))

(deftest get-token-pairs-test
  (test/async done
              (js-invoke (http-client/get-token-pairs-async client {:request token-pairs-request}) "then"
                         (fn [response]
                           (is (= #{:token-a :token-b} (into #{} (-> response first keys))))
                           (done)))))
