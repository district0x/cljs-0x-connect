# cljs-0x-connect

Clojurescript wrapper for the [0xProject/connect](https://github.com/0xProject/0x-monorepo/tree/development/packages/connect) library, which servers as a gateway to any relayer that conforms to the [standard relayer API](https://github.com/0xProject/standard-relayer-api).

## Development:

- [shadow-cljs + cider](#cider)
- [shadow-cljs + lein](#lein)

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
