# cljs-0x-connect

[![Build Status](https://travis-ci.org/district0x/cljs-0x-connect.svg?branch=master)](https://travis-ci.org/district0x/cljs-0x-connect)

Clojurescript wrapper for the [0xProject/connect](https://github.com/0xProject/0x-monorepo/tree/development/packages/connect) library, which servers as a gateway to any relayer that conforms to the [standard relayer API](https://github.com/0xProject/standard-relayer-api).

## Development

Run test suite:

```bash
lein deps
# To run tests and rerun on changes
lein doo chrome tests
```
Install into local repo:

```bash
lein cljsbuild test
lein install
```
