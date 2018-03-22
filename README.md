# cljs-0x-connect

[![Build Status](https://travis-ci.org/district0x/cljs-0x-connect.svg?branch=master)](https://travis-ci.org/district0x/cljs-0x-connect)

Clojurescript wrapper for the [0xProject/connect](https://0xproject.com/docs/connect) library, which servers as a gateway to any relayer that conforms to the [standard relayer API v0](https://github.com/0xProject/standard-relayer-api).


## Installation
Add `[district0x/cljs-0x-connect "1.0.0"]` into your project.clj.<br/>

## Usage

- [cljs-0x-connect.http-client](#http-client)
  - [create-http-client](#create-http-client)
  - [get-fees-async](#get-fees-async)
  - [get-orders-async](#get-orders-async)
  - [get-order-async](#get-order-async)
  - [get-orderbook-async](#get-orderbook-async)
  - [get-token-pairs-async](#get-token-pairs-async)
  - [submit-order-async](#submit-order-async)
- [cljs-0x-connect.ws-orderbook](#ws-orderbook)
  - [create-orderbook-channel](#create-orderbook-channel)
  - [subscribe](#subscribe)
  - [close](#close)

## <a name="cljs-0x-connect.http-client"> `cljs-0x-connect.http-client`

This namespace includes functions for interacting with a set of HTTP endpoints that implement the standard relayer API. <br>
All functions return a JS Promise. Usage example:

```clojure
(ns my-district
  (:require [cljs-0x-connect.http-client :as http-client]))
```

#### <a name="create-http-client"> `create-http-client`

Returns a new HttpClient instance, takes API url string as an argument. <br>
Example:

```clojure
(def client (http-client/create-http-client "https://api.com/"))
```

#### <a name="get-fees-async"> `get-fees-async`

Retrieves fee information from the API, takes a fees request map and an (optional) paging options map (defaults to `{:page 1 :per-page: 100}`) as the arguments. <br>

[Example](https://github.com/district0x/cljs-0x-connect/blob/connect-with-cljsjs/test/tests/all.cljs#L30).

#### <a name="get-orders-async"> `get-orders-async`

Retrieves orders information from the API, takes an order request map and an (optional) paging options map as the arguments.<br>

[Example](https://github.com/district0x/cljs-0x-connect/blob/connect-with-cljsjs/test/tests/all.cljs#L55).

#### <a name="get-order-async"> `get-order-async`

Retrieves information about a specific order from the API, takes a hash of the order as an argument.<br>

[Example](https://github.com/district0x/cljs-0x-connect/blob/connect-with-cljsjs/test/tests/all.cljs#L44).

#### <a name="get-orderbook-async"> `get-orderbook-async`

Retrieves orderbook information from the API, takes an orderbook request map and an (optional) paging options map as the arguments.<br>

[Example](https://github.com/district0x/cljs-0x-connect/blob/connect-with-cljsjs/test/tests/all.cljs#L37).

#### <a name="get-token-pairs-async"> `get-token-pairs-async`

Retrieves token pair information from the API, takes an token request map and an (optional) paging options map as the arguments.<br>

[Example](https://github.com/district0x/cljs-0x-connect/blob/connect-with-cljsjs/test/tests/all.cljs#L62).

#### <a name="submit-order-async"> `submit-order-async`

Submits a signed order to the API, takes a signed order map as an argument<br>
Example:

```clojure
(def signed-order {:ec-signature: {:r "string"
                                   :s "string,"
                                   :v "number"}
                   :exchange-contract-address "string"
                   :expiration-unix-timestamp-sec #object[BigNumber]
                   :fee-fecipient "string"
                   :maker "string"
                   :maker-fee #object[BigNumber]
                   :maker-token-address "string"
                   :maker-token-amount #object[BigNumber]
                   :salt #object[BigNumber]
                   :taker "string"
                   :takerFee #object[BigNumber]
                   :taker-token-address "string"
                   :taker-token-amount #object[BigNumber]})

(http-client/submit-order-async chan signed-order)
```

## <a name="cljs-0x-connect.ws-orderbook"> `cljs-0x-connect.ws-orderbook`

This namesapce includes functions for interacting with a websocket endpoint that implements the standard relayer API. <br>
Example:

```clojure
(ns my-district
  (:require [cljs-0x-connect.ws-orderbook :as ws-orderbook]))
```

#### <a name="create-orderbook-channel"> `create-orderbook-channel`

Returns a new WebSocketOrderbookChannel instance, takes url string as an argument. <br>
Example:

```clojure
(def url "wss://api.radarrelay.com/0x/v0/ws")

(def config {:heartbeat-interval-ms 1000})

(def chan (ws-orderbook/create-orderbook-channel url config))

```

#### <a name="create-channel-handler"> `create-channel-handler`

Returns an OrderbookChannelHandler instance that responds to various channel updates, takes a map of four functions as an argument. <br>
Example:

```clojure
(def handler (ws-orderbook/create-channel-handler {:on-snapshot (fn [chan opts resp] (prn "bids:" (count (aget resp "bids"))
                                                                                          "asks:" (count (aget resp "asks"))))
                                                   :on-update (fn [chan opts order] (prn "new order:" order))
                                                   :on-error (fn [chan opts resp] (prn "Error:" resp))
                                                   :on-close (fn [chan opts] (prn "closing"))}))
```

#### <a name="subscribe"> `subscribe`

Subscribes to orderbook snapshots and updates from the websocket, takes as an argument an OrderbookChannelHandler instance and a map of:

* subscription options map (describing which token pair to subscribe to)
* an OrderbookChannelHandler instance.
Example:

```clojure
(def orderbook-subscription-opts {:base-token-address "0x2956356cd2a2bf3202f771f50d3d14a367b48070"
                                  :quote-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                                  :limit 20
                                  :snapshot true})

(ws-orderbook/subscribe chan {:opts orderbook-subscription-opts
                              :handler handler})
```

#### <a name="close"> `close`

Closes the websocket connection, takes a WebSocketOrderbookChannel instance as an argument. <br>
Example:

```clojure
(ws-orderbook/close chan)
```

## Development

Run test suite:

```bash
lein deps
# To run tests and rerun on changes
lein doo chrome-headless tests
```
Install into local repo:

```bash
lein cljsbuild test
lein install
```
