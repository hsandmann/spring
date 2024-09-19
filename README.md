# Spring Cloud Tutorial

The documentation about the using of this material is available on [https://hsandmann.github.io/spring/](https://hsandmann.github.io/spring/).

## Setup

To use the code in this repository, follow the instructions below:

Create a virtual environment at Python:

``` shell
python3 -m venv env
```

Activate the virtual environment (**you must do this every time you run a script from this repository**):

``` bash
source ./env/bin/activate
```

Install the dependencies with:
``` shell
pip3 install -r requirements.txt
```

## Deployment

The material uses mkdocs to generate the documentation. To view the documentation, run the command:

``` bash
mkdocs serve
```

To release the documentation to GitHub Pages, run the command:

``` bash
mkdocs gh-deploy
```
