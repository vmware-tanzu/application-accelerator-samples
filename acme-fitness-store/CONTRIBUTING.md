# Contributing to the Spring Cloud Services Starters

VMware uses GitHub and accepts code contributions via
[pull requests](https://help.github.com/articles/using-pull-requests).  If you wish to contribute code and
you have not signed our contributor license agreement (CLA), our bot will update the issue when you open a Pull Request.
For any questions about the CLA process, please refer to our [FAQ](https://cla.vmware.com/faq).

Follow these steps to make a contribution to any of our open source repositories:

1. If your contribution includes a change that is exposed to cf CLI users
   (e.g. introducing a new command or flag), please submit an issue
   to discuss it first.
1. Fork the project’s repository and add a remote (e.g. `git remote add my_fork ...`)
1. Create a feature branch (e.g. `git checkout -b my_cool_feature`) and make changes on your branch
1. Build the plugin, run the tests, and check that the plugin works as intended when installed
1. Push to your fork (e.g. `git push my_fork my_cool_feature`) and [submit a pull request](https://help.github.com/articles/creating-a-pull-request)

If you have a CLA on file, your contribution will be analyzed for product fit and engineering quality prior to merging.

Notes:
* All contributions need to be sent using GitHub Pull Requests, preferably consisting of a single commit
* Tests are required for any changes
* We favor small pull requests with a single clearly-defined purpose
* Your pull request is much more likely to be accepted if it has a clear log message which conveys the intent of your change