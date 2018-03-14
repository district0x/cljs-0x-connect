# development:

#### shadow-cljs + cider

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

#### shadow-cljs + lein

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
