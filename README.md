# development:

#### shadow-cljs CLI

1) Start the build:

```bash
yarn
mkdir -p target; cp assets/index.html target/
yarn shadow-cljs watch dev
```

2) Get the cljs REPL:

```bash
yarn shadow-cljs cljs-repl dev
```

#### lein

1) Get the clj REPL:

```bash
lein repl
```

2) start the build and get the cljs REPL:

```clojure
(start-dev!)
```

#### cider & nrepl

1) Start the clj REPL:

```bash
M+x cider-jack-in
```

2) start the build and get the cljs REPL:

```clojure
(start-dev!)
```
