/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package val.com.ciclodevida;

import android.os.Handler;
import android.widget.TextView;

import java.util.List;

public class Utils {

  private static PilaMensajes mPilaMensajes = PilaMensajes.getInstance();

  /**
   * Helper method to print out the lifecycle state of each Activity.  Note this has
   * been wrapped in a Handler to delay the output due to overlaps in lifecycle state
   * changes as one Activity launches another.
   * @link http://developer.android.com/guide/topics/fundamentals/activities.html#CoordinatingActivities
   * @param viewMethods TextView to list out the lifecycle methods called
   * @param viewStatus TextView to list out the status of all Activity classes
   */
  public static void Mostrar(final TextView viewMethods, final TextView viewStatus) {

             //mPilaMensajes, está compartida por todas las actividades

              StringBuilder sbMethods = new StringBuilder();
              List<String> listMethods = mPilaMensajes.getMethodList();
              for (String method : listMethods) {
                  sbMethods.insert(0, method + "\r\n");
              }

              if(viewMethods != null) {
                  viewMethods.setText(sbMethods.toString());
              }

             //MUESTRO EL ESTADO DE LAS ACTIVIDADES
              StringBuilder sbStatus = new StringBuilder();
              for (String key : mPilaMensajes.keySet()) //OBTENGO LAS ACTIVIDADES
              {
                  sbStatus.insert(0,key + ": " + mPilaMensajes.getStatus(key) + "\n"); //Y PARA CADA UNA, SU ESTADO
              }



              if(viewStatus != null) {
                  viewStatus.setText(sbStatus.toString());
              }

    }
}




