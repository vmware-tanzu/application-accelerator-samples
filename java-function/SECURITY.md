# Security Policy

The community has adopted this security disclosure and response policy to ensure we responsibly handle critical issues.

## Supported Versions

We do not have any cadence (N - #) of what versions of our pre-requisites are supported, maintained, or secure, at this time.

## Reporting a Vulnerability - Private Disclosure Process

Security is of the highest importance and all security vulnerabilities or suspected security vulnerabilities should be reported to the java-functions-accelerator team privately, to minimize attacks against current users before they are fixed. Vulnerabilities will be investigated and patched on the next patch (or minor) release as soon as possible. This information could be kept entirely internal to the project.

If you know of a publicly disclosed security vulnerability, please **IMMEDIATELY** contact the VMware Security Team (security@vmware.com). The use of encrypted email is encouraged. The public PGP key can be found [here](https://kb.vmware.com/kb/1055). **IMPORTANT: Do not file public issues on GitHub for security vulnerabilities!**

To report a vulnerability or a security-related issue, please contact the VMware email address with the details of the vulnerability. The email will be fielded by the VMware Security Team and then shared with the Tanzu Community Edition maintainers who have committer and release permissions. Emails will be addressed within 3 business days, including a detailed plan to investigate the issue and any potential workarounds to perform in the meantime. Do not report non-security-impacting bugs through this channel. Use [GitHub issues](https://github.com/sample-accelerators/java-functions-accelerator/issues) instead.

## Proposed Email Content

Provide a descriptive subject line and in the body of the email include the following information:

* Basic identity information, such as your name and your affiliation or company.
* Detailed steps to reproduce the vulnerability  (POC scripts, screenshots, and logs are all helpful to us).
* Description of the effects of the vulnerability on java-functions-accelerator and the related hardware and software configurations, so that the VMware Security Team can reproduce it.
* How the vulnerability affects java-functions-accelerator usage and an estimation of the attack surface, if there is one.
* List other projects or dependencies that were used in conjunction with the software to produce the vulnerability.

## When to Report a Vulnerability

* When you think java-functions-accelerator has a potential security vulnerability.
* When you suspect a potential vulnerability but you are unsure that it impacts java-functions-accelerator.
* When you know of or suspect a potential vulnerability on another project that is used by java-functions-accelerator.

## Confidentiality, Integrity and Availability

We consider vulnerabilities leading to the compromise of data confidentiality, elevation of privilege, or integrity to be our highest priority concerns. Availability, in particular in areas relating to DoS and resource exhaustion, is also a serious security concern. The VMware Security Team takes all vulnerabilities, potential vulnerabilities, and suspected vulnerabilities seriously and will investigate them in an urgent and expeditious manner.

Note that we do not currently consider the default settings for java-functions-accelerator to be secure-by-default. It is necessary for operators to explicitly configure settings, role based access control, and other resource related features in java-functions-accelerator to provide a hardened java-functions-accelerator environment. We will not act on any security disclosure that relates to a lack of safe defaults. Over time, we will work towards improved safe-by-default configuration, taking into account backwards compatibility.

