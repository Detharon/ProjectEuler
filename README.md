# A Guide to Project Euler in Scala

[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)
[![Scala](https://img.shields.io/badge/Scala-3.x-green)](https://www.scala-lang.org/)

This repository will eventually contain solutions for the first 100 problems from the Project Euler website.

> [!WARNING]
> It's still a work in progress. Some solutions are missing, some are not explained and some are only explained in the
> code.

The code is shared for educational purposes, demonstrating how these problems can be approached using Scala.
The focus of the implementations is on readability rather than raw performance, though most of the solutions should
complete within a few seconds on typical hardware.

Where possible, a functional programming style is used, avoiding mutable state and vars.
Some solutions intentionally deviate from this approach for performance comparison; these are marked with an ‘M’ suffix
in their filenames.

While the non-functional style may yield greater efficiency by reducing object allocations, it often results in less
readable and more error-prone code, so it is included primarily for contrast and learning purposes.

## The book format

Problem solutions and the explanations are published on the following site: https://euler.detharon.com

## The Scala project

Every takes form of a runnable Scala object. They're present in `src/main/scala`, while the associated files can be 
found in `src/main/resources`. By convention, all resources are expected to have the same name as the problem they
belong to.

## About Project Euler

https://projecteuler.net/about

Project Euler is a collection of challenging computational and mathematical problems designed to promote learning and
problem-solving. Participants are encouraged to attempt solving each problem independently before consulting existing
solutions.
