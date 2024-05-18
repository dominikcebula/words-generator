[![CI Pipeline](https://github.com/dominikcebula/words-generator/actions/workflows/maven.yml/badge.svg)](https://github.com/dominikcebula/words-generator/actions/workflows/maven.yml)

# Intro

This repository contains "words-generator" application.

It can generate a list of english words possible to create from a given set of characters.

For example, given following letters `glnsu`, it is possible to create two words from them: `lungs`, `slung`.

# Tools used

* [List of English Words](https://github.com/dwyl/english-words)
* Java 21
* picocli
* Lombok
* Logback
* JUnit 5
* AssertJ
* Maven

# How this application works?

Application uses [List of English Words](https://github.com/dwyl/english-words) to create a queryable dataset that is
used to query for english words possible to generate from a given set of characters.

[List of English Words](https://github.com/dwyl/english-words) are kept under `data/words_alpha.txt`. This is a text
file that contains 370k english words.

`data/words_alpha.txt` is then processed and saved as `data/words_alpha_dataset.txt` in a format suitable for querying
for words based on character set.

Queryable dataset uses conceptually the following format:

```
...
word-key1 -> {
    word1,
    word2,
    word3
}
...
word-key2 -> {
    word4,
    word5,
    word6
}
```

Above conceptual format is saved in a text file as:

```
...
K word-key1
V word1
V word2
V word3
...
K word-key2
V word4
V word5
V word6
...
```

`word-key` is the character set representation in a notation that for each character contains count information and
character itself.

For example, following set of characters `abbccc` will be represented as `1a2b3c`. Which means character set contains
one `a` character, two `b` characters and three `c` characters.

Additionally, characters in the key are always sorted. So given `cccabb` it will also encode this key as `1a2b3c`.

That way word like `management`, will be encoded as `2a2e1g2m2n1t`, which is the same as character set for the word
which is `aaeeggmmnnt`.

The mentioned notation for character sets and words, gives then same keys, which is a foundation block for dataset
creation and query ability.

For example, below three words will be all encoded as the same key:

```
dabbler -> 1a2b1d1e1l1r
drabble -> 1a2b1d1e1l1r
rabbled -> 1a2b1d1e1l1r
```

When querying for words based on the character set `delbbra`, this character set will also be encoded as `1a2b1d1e1l1r`.

Conceptually such an information will be saved in a dataset as:

```
1a2b1d1e1l1r -> {
    dabbler,
    drabble
    rabbled
}
```

In the dataset text format it will be saved as:

```
K 1a2b1d1e1l1r
V dabbler
V drabble
V rabbled
```

So when character set `delbbra` is given, it will be encoded as `1a2b1d1e1l1r`, query will be executed against dataset
using this key, which will give `dabbler`, `drabble`, `rabbled` as a result.

# Building project

Execute the below command to compile and build project:

```shell
mvn clean install
```

# Usage

## Using pre-generated dataset

This repository already contains `words_alpha_dataset.txt` which is used by this application to output english words
possible to generate from a given character set. If there is no need to regenerate this dataset, you can just run the
application like below:

```shell
java -jar target/words-generator.jar query-words -data-set-file-path data/words_alpha_dataset.txt -characters glnsu
```

Above command will generate list of english words possible to generate from characters `glnsu`.

This will result in following words being visible on the output:

```shell
lungs
slung
```

## Regenerating dataset

If there is a need to have a fresh dataset generated from english words dictionary, below command can be used for this
purpose:

```shell
java -jar target/words-generator.jar generate-dataset -words-file-path data//words_alpha.txt -data-set-output-file-path data/words_alpha_dataset.txt
```

Above command will read `words_alpha.txt` and will generate `words_alpha_dataset.txt` based on it.
`words_alpha_dataset.txt` will be then used in the `query-words` described in the previous paragraph to output list of
english words possible to generate based on given set of characters.

# Author

Dominik Cebula

* https://dominikcebula.com/
* https://blog.dominikcebula.com/
* https://www.udemy.com/user/dominik-cebula/
