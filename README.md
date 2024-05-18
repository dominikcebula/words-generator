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
