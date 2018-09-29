# NEUTRON SCATTERING COLOR PERCEPTION DEMO #

## ABOUT
Designed by David Senter, ORNL CDA HERE Undergraduate Intern, Summer 2016  
Mentor: Dr. Chad A. Steed, Computational Data Analytics Group, Computational Sciences and Engineering Division, Oak Ridge National Laboratory  
Additional thanks to Dr. Thomas Proffen and Dr. Garret Granroth of the Neutron Data Analysis and Visualization Division

This application demonstrates the application of concepts of color perception to neutron scattering data.
It is written in Processing, a programming platform based on Java and made for easier visual design and quick prototypes.
The main sketch file is DataMatrixSketch.pde, which can be opened and edited in Processing.  
For more information and to download Processing: http://processing.org  

## FEATURES

### NAVIGATION
Use the up/down arrow keys or mouse scrolling to change the selected slice. 
X-, y-, and z-values are visible for the cell hovered by the mouse, as well as signal value and mapped color.
To change color maps, use the left/right arrow keys or the arrow buttons on the window.

### AUTOMATIC SCALING
The scale of the data is automatically set for each slice after removing outliers.
A subset of the data can be selected for auto-scaling by dragging a rectangle around the desired region.

### CUSTOM SCALING
Move the left, middle, and right sliders to customize the color scale. The histogram shows where the data lies along the scale.

### ANIMATION
Click the animation button to activate scale and color map animation. To animate only the scale or color map, use the settings in the lower right corner.

<figcaption>Animation example</figcaption><img src="https://raw.githubusercontent.com/davidsenter/neutron-scattering-color-perception-demo/master/images/animation_example.gif" alt="Heated Map" width="400">

### COLOR MAPS
Currently available maps are heated, grayscale, blue, and rainbow. Use left/right arrow keys to change the color map.
Click "Invert Scale" to invert the order of colors in the current map.

<figcaption>"Heated" Color Scale</figcaption><img src="https://raw.githubusercontent.com/davidsenter/neutron-scattering-color-perception-demo/master/images/default_screen.png" alt="Heated Map" width="400">
<figcaption>"Heated" Color Scale after inversion</figcaption><img src="https://raw.githubusercontent.com/davidsenter/neutron-scattering-color-perception-demo/master/images/inverted_screen.png" alt="Heated Map" width="400"><figcaption>Rainbow Color Scale</figcaption><img src="https://raw.githubusercontent.com/davidsenter/neutron-scattering-color-perception-demo/master/images/rainbow_screen.png" alt="Rainbow Map" width="400">

### EXPORTING COLOR MAPS
Click "Export Color Map" to export the current map at the current scale.
Save the file in the desired location as a .map file, which can then be opened in Mantid.
