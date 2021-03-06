/*
 * Author: Jon Trulson <jtrulson@ics.com>
 * Copyright (c) 2016 Intel Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the The MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 */

import upm_ozw.AeotecDW2E;

public class AeotecDW2E_Example
{
    private static String defaultDev = "/dev/ttyACM0";

    public static void main(String[] args) throws InterruptedException
    {
// ! [Interesting]

        if (args.length > 0)
            defaultDev = args[0];
        System.out.println("Using device " + defaultDev);

        // Instantiate an Aeotec Door/Window 2nd Edition sensor
        // instance, on device node 10.  You will almost certainly
        // need to change this to reflect your own network.  Use the
        // ozwdump example to see what nodes are available.
        AeotecDW2E sensor = new AeotecDW2E(10);

        // The first thing to do is create options, then lock them when done.
        sensor.optionsCreate();
        sensor.optionsLock();

        System.out.println("Initializing, this may take awhile depending "
                           + "on your ZWave network");

        sensor.init(defaultDev);
        System.out.println("Initialization complete");

        System.out.println("Querying data...");

        while (true)
            {
                if (sensor.isDeviceAvailable())
                    {
                        System.out.println("Alarm status: "
                                           + sensor.isAlarmTripped());

                        System.out.println("Tamper Switch status: "
                                           + sensor.isTamperTripped());

                        System.out.println("Battery Level: "
                                           + sensor.getBatteryLevel()
                                           + "%");

                        System.out.println();
                    }
                else
                    {
                        System.out.println("Device has not yet responded "
                                           + "to probe.");
                        System.out.println("Try waking it, or wait until "
                                           + "it wakes itself if "
                                           + "configured to do so.");

                        System.out.println();
                    }

                Thread.sleep(1000);
            }

// ! [Interesting]
    }
}
