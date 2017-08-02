
all: .gitignore

.gitignore: .gitignore.in
	rm -f $@
	sed -e 's/ *#.*$$//' <$< >$@
	chmod -w $@

tarball:
	tar czf "../abpdraft-`date +%Y-%m-%d_%H%M`.tar.gz" -C .. abpdraft/

ChangeLog:
	gitcl | gitcl2 -t uni -r -m 20 -n 2 >ChangeLog

