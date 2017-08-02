SCSS = scss
SCSSFLAGS = --sourcemap=none
SHELL = bash

all-css: \
    module1/index.css \
    skel/skel.css

%.css: %.scss config.scss skel/
	$(SCSS) $(SCSSFLAGS) $< $@

clean: 
	rm -f \
	    module1/index.css

auto-build: editor
	onchange \
	    -f . \
	    -f skel \
        make

editor:
	shopt -s nullglob; \
	pluma *.html *.scss js/* skel/*.js skel/*.scss &

templatedir=/mnt/istore/job/index/p170630
sync-skel:
	rsync -amv --delete ${templatedir}/libjs/* libjs
	rsync -amv --delete ${templatedir}/skel/* skel

symlinks:
	ln -snf \
	    /usr/share/javascript/createjs/ \
	    /usr/share/javascript/jquery/ \
	    /usr/share/javascript/jquery-flot/ \
	    /usr/share/javascript/jquery-ui/ \
	    /usr/share/javascript/zwk/ \
	    /usr/share/javascript/vuejs/ \
	    libjs/
	ln -snf \
	    /usr/share/fonts-font-awesome \
	    libfont/font-awesome

