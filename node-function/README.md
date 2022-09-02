# Node Function

This repo contains a simple Node Function that can be deployed as a TAP workload.

This function utilizes the buildpacks provided by VMware's open-source [Function Buildpacks for Knative](https://github.com/vmware-tanzu/function-buildpacks-for-knative) project.

## Getting Started

To begin editing your function, modify `index.js` in the root directory.

Inside this file, you will find a function that is invoked by default. For example:

```
module.exports = async function sampleFunction(context) {
  const ret = 'This is a sample function';
  return new Promise((resolve, reject) => {
    setTimeout(_ => {
      context.log.info('sending response to client')
      resolve(ret);
    }, 500);
  });
};
```

You may replace the code inside this default function with your logic.

## Deploying

Please see [DEPLOYING.md](DEPLOYING.md) on how to build, deploy, and test your newly built function.
