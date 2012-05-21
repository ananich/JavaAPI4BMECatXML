package org.omnaest.api4bmecatxml.bmecat12.utils;

import com.rits.cloning.Cloner;

/**
 * Helper to allow deep cloning
 * 
 * @author Omnaest
 */
public class CloningHelper
{
  /**
   * Clones the given instance
   * 
   * @param instance
   * @return
   */
  public static <T> T clone( T instance )
  {
    T deepClone = new Cloner().deepClone( instance );
    return deepClone;
  }
}
