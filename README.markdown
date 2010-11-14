### Mirth

Mirth is a superficial
[stack-oriented](http://en.wikipedia.org/wiki/Stack-oriented_programming_language)
programming language (Forth, Factor, etc.), designed with help from [boredomist/froth](https://github.com/boredomist/froth).

### Running

* `git clone git://github.com/MayDaniel/mirth.git`
* `cd mirth`
* `lein deps` or `cake deps`

#### REPL

* `sh mirth`

#### Files

* `sh mirth filename`

### Examples

    > 1 2 + .
    3

    > 5 dup .S
    [5 5]

    > 55.5 10 swap .S
    [55.5 10]

    > var x 10
