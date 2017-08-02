SHELL = /bin/bash

SCSS = scss
SCSSFLAGS = --sourcemap=none

templatedir = .template
modmodels = $(shell find -name '*/index-model.js')
mod_DIRS = $(patsubst %/index-model.js, %, $(modmodels))

scss_SOURCES = $(shell find -name '*.scss')
scss_DIRS = $(dir $(scss_SOURCES))
css_OBJECTS = $(patsubst %.scss, %.css, $(scss_SOURCES))
OBJECTS = $(css_OBJECTS)

%.css: %.scss
	$(SCSS) $(SCSSFLAGS) "$<" "$@"

all: $(OBJECTS)

clean: 
	rm -f $(OBJECTS)

auto-build:
	onchange $(patsubst %, -f %, $(scss_DIRS)) make all

sync-skel:
	rsync -amv --delete ${templatedir}/libjs/* libjs
	rsync -amv --delete ${templatedir}/skel/* skel

