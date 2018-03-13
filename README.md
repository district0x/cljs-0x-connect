# development:

#### shadow-cljs CLI

1) Install dependencies:

```bash
yarn
```

2) Start the build:

```bash
yarn shadow-cljs watch dev
```

or

```bash
yarn dev
```

3) Get the cljs REPL:

```bash
yarn shadow-cljs cljs-repl dev
```

#### lein

1) Install dependencies:

```bash
yarn
```

2) Get the clj REPL:

```bash
lein repl
```

3) start the build and get the cljs REPL:

```clojure
(start-dev!)
```

#### cider & nrepl

1) Install dependencies:

```bash
yarn
```

2) Start the clj REPL:

```emacs
M+x cider-jack-in
```

3) start the build and get the cljs REPL:

```clojure
(start-dev!)
```
