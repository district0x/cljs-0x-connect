# cljs-0x-connect

Clojurescript wrapper for the [0xProject/connect](https://github.com/0xProject/0x-monorepo/tree/development/packages/connect) library, which servers as a gateway to any relayer that conforms to the [standard relayer API](https://github.com/0xProject/standard-relayer-api).

## Development:

- [shadow-cljs + cider](#cider)
- [shadow-cljs + lein](#lein)
- [testing with shadow-cljs + karma](#karma)
  - [running tests in a CI environment](#ci)

## <a name="cider">shadow-cljs + cider

1) Install npm dependencies:

```bash
yarn
```

2) Start the clj REPL:

```emacs
M+x cider-jack-in
```

3) Start the server, build watcher and get the cljs REPL:

```clojure
(start-dev!)
```

4) Point your browser to `localhost:8080`

## <a name="lein">shadow-cljs + lein

1) Install npm dependencies:

```bash
yarn
```

2) Start the clj REPL:

```bash
lein repl
```

3) Start the server, build watcher and get the cljs REPL:

```clojure
(start-dev!)
```

4) Point your browser to `localhost:8080`

## <a name="karma">testing with shadow-cljs + karma

#### <a name="ci"> running tests in CI environment

Compile and run you tests once from the command line, and get back a status code:

```bash
yarn shadow-cljs compile ci
yarn karma start --single-run
```

You can also start a watcher and continuously evaluate the test:

```bash
yarn shadow-cljs watch ci
```

in a separate terminal buffer:

```bash
yarn karma start
```
