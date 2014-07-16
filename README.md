IMage PROcessing
=========

It is a image processing library

  - Type some Markdown text in the left window
  - See the HTML in the right
  - Magic

Markdown is a lightweight markup language based on the formatting conventions that people naturally use in email.  As [John Gruber] writes on the [Markdown site] [1]:

> Have fun :)

This text you see here is *actually* written in Markdown! To get a feel for Markdown's syntax, type some text into the left window and watch the results in the right.  

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
  - Sepia
  - ...

Convolution (using convolve)
  - Blur
  - Sharpen
  - Emboss
  - ...

Mathematical morphology
  - Dilation
  - Erosion

Noise removal
  - Medain filter (also parallel implementation)

Other
  - Resize
  - Connected Component

License
----

MIT


**Free Software, Hell Yeah!**
