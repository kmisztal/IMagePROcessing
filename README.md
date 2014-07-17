IMage PROcessing
=========

It is a image processing library

  - Supprot for each plugin creating
  - Easy parallelization (some of plugins are prepared with parallel implamentation)
  - Executors (also with simple GUI)

> Have fun :)

Version
----

0.1

Installation
--------------

```sh
git clone [git-repo-url] dillinger
cd dillinger
npm i -d
mkdir -p public/files/{md,html,pdf}
```

Plugins
--------------

Color transform (based on LookUpTable)
  - Invert
  - Posterize
  - Threshold
  - Multi level Otsu thresholding
  - Sepia

Convolution (using convolve)
  - Blur
  - Sharpen
  - Emboss

Mathematical morphology
  - Dilation
  - Erosion

Noise removal
  - Medain filter (also parallel implementation)

Noise generators
  - Salt and Peper

Other
  - Resize
  - Connected Component
  - FloodFill

License
----

MIT


**Free Software, Hell Yeah!**
