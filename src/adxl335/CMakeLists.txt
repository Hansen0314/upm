set (libname "adxl335")
set (libdescription "Low-power, 3-axis +/- 3 g Accelerometer")
set (module_src ${libname}.cxx)
set (module_hpp ${libname}.hpp)
set (module_iface iAcceleration.hpp)
upm_module_init(mraa)
