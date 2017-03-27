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

import java.util.*;

public class PilaMensajes {

  private Map<String, String> mapaActividad_Estado;
  private List<String> pilaMetodosInvocados;
  private static PilaMensajes ourInstance = new PilaMensajes(); //Patron Singleton
  private static final String STATUS_SUFFIX = "ed";

  public static PilaMensajes getInstance() {
    return ourInstance;
  }

    //Patron singleton
  private PilaMensajes() {
    mapaActividad_Estado = new LinkedHashMap<String, String>();
    pilaMetodosInvocados = new ArrayList<String>();
  }

  public List<String> getMethodList() {
    return pilaMetodosInvocados;
  }

  public void clear() {
    pilaMetodosInvocados.clear();
    mapaActividad_Estado.clear();
  }

  /**
   * Actualiza la lista de métodos invoados y el estado de la actividad
   *
   * @param activityName
   * @param status
   */
  public void setStatus(String activityName, String status) {

      pilaMetodosInvocados.add(activityName + "." + status + "()");//añado el método invocado
      if (mapaActividad_Estado.containsKey(activityName))  //si la actividad existe en el mapa de estados
            mapaActividad_Estado.remove(activityName); //la reemplazo
      mapaActividad_Estado.put(activityName, status);
  }

  /**
   * Dado el nombre de una actividad, devuelve su estado.
   *
   * @param activityName
   * @return
   */
  public String getStatus(String activityName) {
    String status = mapaActividad_Estado.get(activityName);
    return status;
  }

    /**
     *
     * @return Devuelve las claves, es decir, las actividades presentes en el mapa
     */
  public Set<String> keySet() {
    return mapaActividad_Estado.keySet();
  }
}
