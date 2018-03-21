# cljs-0x-connect

[![Build Status](https://travis-ci.org/district0x/cljs-0x-connect.svg?branch=master)](https://travis-ci.org/district0x/cljs-0x-connect)

Clojurescript wrapper for the [0xProject/connect](https://github.com/0xProject/0x-monorepo/tree/development/packages/connect) library, which servers as a gateway to any relayer that conforms to the [standard relayer API](https://github.com/0xProject/standard-relayer-api).

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

#### <a name="create-http-client"> `create-http-client`

Returns a new HttpClient instance.

#### <a name="get-fees-async"> `get-fees-async`
#### <a name="get-orders-async"> `get-orders-async`
#### <a name="get-order-async"> `get-order-async`
#### <a name="get-orderbook-async"> `get-orderbook-async`
#### <a name="get-token-pairs-async"> `get-token-pairs-async`
#### <a name="submit-order-async"> `submit-order-async`

## <a name="cljs-0x-connect.ws-orderbook"> `cljs-0x-connect.ws-orderbook`

```clojure
(ns my-district
  (:require [cljs-0x-connect.ws-orderbook :as ws-orderbook]))
```

#### <a name="create-orderbook-channel"> `create-orderbook-channel`

```clojure
(def url "wss://api.radarrelay.com/0x/v0/ws")

(def config {:heartbeat-interval-ms 1000})

(def chan (ws-orderbook/create-orderbook-channel url config))

```

#### <a name="create-channel-handler"> `create-channel-handler`

```clojure
(def handler (ws-orderbook/create-channel-handler {:on-snapshot (fn [chan opts resp] (prn "bids:" (count (aget resp "bids"))
                                                                                          "asks:" (count (aget resp "asks"))))
                                                   :on-update (fn [chan opts order] (prn "new order:" order))
                                                   :on-error (fn [chan opts resp] (prn "Error:" resp))
                                                   :on-close (fn [chan opts] (prn "closing"))}))
```

#### <a name="subscribe"> `subscribe`

```clojure
(ws-orderbook/subscribe chan {:opts orderbook-subscription-opts
                              :handler handler})
```

#### <a name="close"> `close`

## Usage

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
