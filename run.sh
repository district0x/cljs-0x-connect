#!/bin/bash

yarn; mkdir -p target; cp assets/index.html target/; yarn shadow-cljs watch app 
