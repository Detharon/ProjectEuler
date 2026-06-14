# About this guide

This guide contains solutions for the first 100 problems from the Project Euler website.
Full code, in a form of runnable classes, is available on GitHub: https://github.com/Detharon/ProjectEuler

The code is shared for educational purposes, demonstrating how these problems can be approached using Scala.
There are many other sites that discuss Project Euler problems, but here, the purpose it twofold:
discuss the problem while emphasizing Scala's strengths of making efficient code that's easy to understand.

So, whenever possible, a functional programming style is used, avoiding mutable state and vars. Some solutions
intentionally deviate from this approach for performance comparison;
these are marked with an ‘M’ suffix in their filenames.

While the non-functional style may yield greater efficiency by reducing object allocations, it often results in less
readable and more error-prone code, so it is included primarily for contrast and learning purposes.

## How to use this guide

My advice would be to read the solutions only when you've already solved the problem. It's normal to struggle at the
beginning. The first solution can be unoptimized and too slow to give the final result, but it's a good starting
point to think what can be improved.

## Contributing

Even though it's an individual work, that's heavily influenced by my personal style, I'm open for contributions.
If you want to add your some extra insights, you've found an error or a mistake, please send a pull request
or raise an issue in GitHub.

## Project status

The project it still in progress, with many problems missing.
Some of them are solved but not described yet in this book.
