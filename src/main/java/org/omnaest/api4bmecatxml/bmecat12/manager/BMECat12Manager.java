/*******************************************************************************
 * Copyright (c) 2012 Danny Kunz.
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.omnaest.api4bmecatxml.bmecat12.manager;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.parsers.SAXParserFactory;

import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12;
import org.omnaest.utils.events.exception.basic.ExceptionHandlerEPrintStackTrace;
import org.omnaest.utils.xml.JAXBXMLHelper;
import org.omnaest.utils.xml.JAXBXMLHelper.UnmarshallingConfiguration;
import org.omnaest.utils.xml.JAXBXMLHelper.UnmarshallingConfiguration.Configurator;

/**
 * Simple manager around the {@link BMECat12} data model
 * 
 * @author Omnaest
 */
public class BMECat12Manager
{
  /**
   * Loads a {@link BMECat12} from a given {@link InputStream} providing XML content
   * 
   * @param inputStream
   *          {@link InputStream}
   * @return new {@link BMECat12} instance
   */
  public static BMECat12 loadFrom( InputStream inputStream )
  {
    //
    final UnmarshallingConfiguration unmarshallingConfiguration = new UnmarshallingConfiguration().setExceptionHandler( new ExceptionHandlerEPrintStackTrace() )
                                                                                                  .setConfigurator( new Configurator()
                                                                                                                    {
                                                                                                                      @Override
                                                                                                                      public void configure( SAXParserFactory saxParserFactory ) throws Exception
                                                                                                                      {
                                                                                                                        saxParserFactory.setFeature( "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                                                                                                                                                     false );
                                                                                                                      }
                                                                                                                    } );
    
    BMECat12 bmeCat12 = JAXBXMLHelper.loadObjectFromXML( inputStream, BMECat12.class, unmarshallingConfiguration );
    
    //
    return bmeCat12;
  }
  
  /**
   * Stores a given {@link BMECat12} instance to the given {@link OutputStream}
   * 
   * @param outputStream
   *          {@link OutputStream}
   * @param bmeCat12
   *          {@link BMECat12}
   */
  public static void storeTo( OutputStream outputStream, BMECat12 bmeCat12 )
  {
    JAXBXMLHelper.storeObjectAsXML( bmeCat12, outputStream );
  }
  
  /**
   * Simple validation of a {@link BMECat12} using any available {@link Validation} instance
   * 
   * @param bmeCat12
   * @return {@link Set} of {@link ConstraintViolation}s
   */
  public static Set<ConstraintViolation<BMECat12>> validate( BMECat12 bmeCat12 )
  {
    //
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<BMECat12>> constraintViolationSet = validator.validate( bmeCat12 );
    
    //
    return constraintViolationSet;
  }
}
