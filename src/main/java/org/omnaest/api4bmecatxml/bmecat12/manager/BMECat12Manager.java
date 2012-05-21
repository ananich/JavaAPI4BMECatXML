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
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParserFactory;

import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFArticles;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ArticleToCatalogGroupMap;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.CatalogGroupSystem;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.FeatureSystem;
import org.omnaest.utils.events.exception.ExceptionHandler;
import org.omnaest.utils.events.exception.basic.ExceptionHandlerEPrintStackTrace;
import org.omnaest.utils.xml.JAXBXMLHelper;
import org.omnaest.utils.xml.JAXBXMLHelper.UnmarshallingConfiguration;
import org.omnaest.utils.xml.JAXBXMLHelper.UnmarshallingConfiguration.Configurator;
import org.omnaest.utils.xml.XMLIteratorFactory;

/**
 * Simple manager around the {@link BMECat12} data model
 * 
 * @author Omnaest
 */
public class BMECat12Manager
{
  
  /* ********************************************** Classes/Interfaces ********************************************** */
  
  public static interface StreamFactory
  {
    public static interface SFHeader
    {
      /**
       * Returns the {@link Header}
       * 
       * @return
       */
      public Header getHeader();
      
      /**
       * @return {@link SFFeatureSystem}
       */
      public SFFeatureSystem featureSystem();
      
      /**
       * @return {@link SFCatalogGroupSystem}
       */
      public SFCatalogGroupSystem catalogGroupSystem();
      
      /**
       * @return {@link SFArticles}
       */
      public SFArticles articles();
      
    }
    
    public static interface SFFeatureSystem
    {
      /**
       * @return {@link Iterator} of all available {@link FeatureSystem}s
       */
      public Iterator<FeatureSystem> newFeatureSystemIterator();
      
      /**
       * @return {@link SFClassificationSystem}
       */
      public SFClassificationSystem classificationSystem();
      
    }
    
    public static interface SFClassificationSystem
    {
      
      /**
       * @return {@link Iterator} of all available {@link ClassificationSystem}s
       */
      public Iterator<ClassificationSystem> newClassificationSystemIterator();
      
      /**
       * @return {@link SFCatalogGroupSystem}
       */
      public SFCatalogGroupSystem catalogGroupSystem();
      
    }
    
    public static interface SFCatalogGroupSystem
    {
      /**
       * @return an available {@link CatalogGroupSystem}
       */
      public CatalogGroupSystem getCatalogGroupSystem();
      
      /**
       * @return {@link SFArticles}
       */
      public SFArticles articles();
      
    }
    
    public static interface SFArticles
    {
      /**
       * @return {@link Iterator} of all available {@link Article}s
       */
      public Iterator<Article> newArticleIterator();
      
      /**
       * @return {@link SFArticleToCatalogGroupMap}
       */
      public SFArticleToCatalogGroupMap articleToCatalogGroupMap();
      
      /**
       * Closes the {@link StreamFactory} and the underlying {@link InputStream}
       */
      public void close();
      
    }
    
    public static interface SFArticleToCatalogGroupMap
    {
      
      /**
       * @return {@link Iterator} of all available {@link ArticleToCatalogGroupMap}s
       */
      public Iterator<ArticleToCatalogGroupMap> newArticleToCatalogGroupMapIterator();
      
      /**
       * Closes the {@link StreamFactory} and the underlying {@link InputStream}
       */
      public void close();
    }
  }
  
  /**
   * @author Omnaest
   */
  public static class StreamFactoryImpl implements StreamFactory.SFHeader, StreamFactory.SFFeatureSystem,
                                       StreamFactory.SFClassificationSystem, StreamFactory.SFCatalogGroupSystem,
                                       StreamFactory.SFArticles, StreamFactory.SFArticleToCatalogGroupMap
  {
    /* ********************************************** Variables / State ********************************************** */
    private final InputStream        inputStream;
    private final XMLIteratorFactory xmlIteratorFactory;
    
    /* ********************************************** Methods ********************************************** */
    
    /**
     * @see StreamFactory
     * @param inputStream
     * @param exceptionHandler
     */
    protected StreamFactoryImpl( InputStream inputStream, ExceptionHandler exceptionHandler )
    {
      super();
      this.xmlIteratorFactory = new XMLIteratorFactory( inputStream, exceptionHandler );
      this.inputStream = inputStream;
    }
    
    @Override
    public Header getHeader()
    {
      //
      Header retval = null;
      
      //
      final Iterator<Header> iterator = this.xmlIteratorFactory.doAddXMLTagScope( new QName( BMECat12.NAMESPACE, "HEADER" ) )
                                                               .doAddXMLTagTouchBarrier( new QName( BMECat12.NAMESPACE,
                                                                                                    "FEATURE_SYSTEM" ) )
                                                               .newIterator( Header.class );
      if ( iterator != null && iterator.hasNext() )
      {
        retval = iterator.next();
      }
      
      //
      return retval;
    }
    
    @Override
    public Iterator<FeatureSystem> newFeatureSystemIterator()
    {
      return this.xmlIteratorFactory.doAddXMLTagScope( new QName( BMECat12.NAMESPACE, "FEATURE_SYSTEM" ) )
                                    .doAddXMLTagTouchBarrier( new QName( BMECat12.NAMESPACE, "CLASSIFICATION_SYSTEM" ) )
                                    .newIterator( FeatureSystem.class );
    }
    
    @Override
    public Iterator<ClassificationSystem> newClassificationSystemIterator()
    {
      return this.xmlIteratorFactory.doAddXMLTagScope( new QName( BMECat12.NAMESPACE, "CLASSIFICATION_SYSTEM" ) )
                                    .doAddXMLTagTouchBarrier( new QName( BMECat12.NAMESPACE, "CATALOG_GROUP_SYSTEM" ) )
                                    .newIterator( ClassificationSystem.class );
    }
    
    @Override
    public CatalogGroupSystem getCatalogGroupSystem()
    {
      //
      CatalogGroupSystem retval = null;
      
      //
      final Iterator<CatalogGroupSystem> iterator = this.xmlIteratorFactory.doAddXMLTagScope( new QName( BMECat12.NAMESPACE,
                                                                                                         "CATALOG_GROUP_SYSTEM" ) )
                                                                           .doAddXMLTagTouchBarrier( new QName(
                                                                                                                BMECat12.NAMESPACE,
                                                                                                                "ARTICLE" ) )
                                                                           .newIterator( CatalogGroupSystem.class );
      if ( iterator != null && iterator.hasNext() )
      {
        retval = iterator.next();
      }
      
      //
      return retval;
    }
    
    @Override
    public Iterator<Article> newArticleIterator()
    {
      return this.xmlIteratorFactory.doAddXMLTagTouchBarrier( new QName( BMECat12.NAMESPACE, "ARTICLE_TO_CATALOGGROUP_MAP" ) )
                                    .newIterator( Article.class );
    }
    
    @Override
    public Iterator<ArticleToCatalogGroupMap> newArticleToCatalogGroupMapIterator()
    {
      return this.xmlIteratorFactory.doAddXMLTagScope( new QName( BMECat12.NAMESPACE, "ARTICLE_TO_CATALOGGROUP_MAP" ) )
                                    .newIterator( ArticleToCatalogGroupMap.class );
    }
    
    @Override
    public void close()
    {
      try
      {
        this.xmlIteratorFactory.close();
        this.inputStream.close();
      }
      catch ( Exception e )
      {
      }
    }
    
    @Override
    public StreamFactory.SFArticleToCatalogGroupMap articleToCatalogGroupMap()
    {
      return this;
    }
    
    @Override
    public SFArticles articles()
    {
      return this;
    }
    
    @Override
    public StreamFactory.SFCatalogGroupSystem catalogGroupSystem()
    {
      return this;
    }
    
    @Override
    public StreamFactory.SFClassificationSystem classificationSystem()
    {
      return this;
    }
    
    @Override
    public StreamFactory.SFFeatureSystem featureSystem()
    {
      return this;
    }
  }
  
  /* ********************************************** Methods ********************************************** */
  
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
   * Returns a new {@link StreamFactory} instance
   * 
   * @param inputStream
   *          {@link InputStream}
   * @param exceptionHandler
   *          {@link ExceptionHandler}
   * @return new {@link StreamFactory} instance
   */
  public static StreamFactory.SFHeader newStreamFactory( InputStream inputStream, ExceptionHandler exceptionHandler )
  {
    return new StreamFactoryImpl( inputStream, exceptionHandler );
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
