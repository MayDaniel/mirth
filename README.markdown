### Mirth

Mirth is a simple stack-oriented programming language.

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
    [10 55.5]

    > var x 10

    > def sq dup *

    > x sq .
    100
