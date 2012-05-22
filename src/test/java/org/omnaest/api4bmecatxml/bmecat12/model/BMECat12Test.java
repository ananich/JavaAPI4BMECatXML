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
package org.omnaest.api4bmecatxml.bmecat12.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFArticleToCatalogGroupMap;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFArticles;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFCatalogGroupSystem;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFClassificationSystem;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFFeatureSystem;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager.StreamFactory.SFHeader;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Address;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Agreement;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Agreement.DateTime.Type;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Buyer;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Buyer.BuyerId;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Catalog;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Catalog.PriceFlag;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Supplier;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.Header.Supplier.SupplierId;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticleDetails;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticleDetails.BuyerAid;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticleDetails.SpecialTreatmentClass;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticleFeatures;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticleFeatures.Feature;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticlePriceDetails;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.Article.ArticlePriceDetails.ArticlePrice;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ArticleToCatalogGroupMap;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.CatalogGroupSystem;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.CatalogGroupSystem.CatalogStructure;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem.ClassificationGroups;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem.ClassificationGroups.ClassificationGroup;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem.ClassificationSystemFeatureTemplates;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem.ClassificationSystemFeatureTemplates.ClassificationSystemFeatureTemplate;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.FeatureSystem;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.FeatureSystem.FeatureGroup;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TNewCatalog;
import org.omnaest.utils.events.exception.basic.ExceptionHandlerEPrintStackTrace;
import org.omnaest.utils.operation.foreach.Range;
import org.omnaest.utils.structure.collection.CollectionUtils;
import org.omnaest.utils.structure.collection.list.ListUtils;
import org.omnaest.utils.structure.container.ByteArrayContainer;
import org.omnaest.utils.time.DurationCapture;

/**
 * @see BMECat12
 * @author Omnaest
 */
public class BMECat12Test
{
  
  @Test
  public void testSmall()
  {
    //   
    Header header = new Header().setCatalog( new Catalog().setCatalogId( "Example catalog id" )
                                                          .setCatalogName( "Example catalog" )
                                                          .setCatalogVersion( "001.001" )
                                                          .setLanguage( "de" ) )
                                .setSupplier( new Supplier().setSupplierName( "Supplier 1" ) )
                                .setBuyer( new Buyer().setBuyerName( "Buyer 1" ) );
    TNewCatalog tNewCatalog = new TNewCatalog().add( new Article().setSupplierAid( "supplier 1" )
                                                                  .add( new ArticlePriceDetails().add( new ArticlePrice() ) ) )
                                               .add( new Article().setSupplierAid( "supplier 1" )
                                                                  .add( new ArticlePriceDetails().add( new ArticlePrice() ) ) )
                                               .add( new Article().setSupplierAid( "supplier 1" )
                                                                  .add( new ArticlePriceDetails().add( new ArticlePrice() ) ) );
    BMECat12 bmeCat12 = new BMECat12().setHeader( header ).setTNewCatalog( tNewCatalog );
    
    Set<ConstraintViolation<BMECat12>> constraintViolationSet = BMECat12Manager.validate( bmeCat12 );
    System.out.println( CollectionUtils.toString( constraintViolationSet ) );
    assertTrue( constraintViolationSet.isEmpty() );
    
    //
    ByteArrayContainer byteArrayContainer = new ByteArrayContainer();
    BMECat12Manager.storeTo( byteArrayContainer.getOutputStream(), bmeCat12 );
    System.out.println( byteArrayContainer );
  }
  
  @Test
  public void testModel()
  {
    //   
    final Header header = new Header().setBuyer( new Buyer().setAddress( new Address().setType( Address.Type.buyer )
                                                                                      .setName( "name" )
                                                                                      .setContact( "contact" ) )
                                                            .setBuyerId( new BuyerId().setType( BuyerId.Type.buyer_specific )
                                                                                      .setValue( "buyer id" ) )
                                                            .setBuyerName( "buyer name" ) )
                                      .setCatalog( new Catalog().setCatalogId( "Catalog id 01" )
                                                                .setCatalogName( "Catalog 1" )
                                                                .setCatalogVersion( "001.001" )
                                                                .setCurrency( "EUR" )
                                                                .setLanguage( "de" )
                                                                .addTerritory( "sjflsjflkjskjflkjsf" )
                                                                .add( new PriceFlag().setType( PriceFlag.Type.incl_packing )
                                                                                     .setValue( true ) )
                                                                .setDateTime( new Catalog.DateTime().setDate( "2012-01-01" )
                                                                                                    .setTime( "08:00:00" )
                                                                                                    .setTimezone( "GMT +1:00" ) ) )
                                      .setSupplier( new Supplier().setSupplierName( "supplier" )
                                                                  .add( new SupplierId().setType( SupplierId.Type.buyer_specific )
                                                                                        .setValue( "supplier id" ) )
                                                                  .setAddress( new Address().setType( Address.Type.supplier )
                                                                                            .setCity( "city" )
                                                                                            .setName( "name" )
                                                                                            .setStreet( "street" ) ) )
                                      .add( new Agreement().setAgreementId( "agreemen id" )
                                                           .add( new Agreement.DateTime().setType( Type.agreement_start_date )
                                                                                         .setDate( "2012-01-01" ) )
                                                           .add( new Agreement.DateTime().setType( Type.agreement_end_date )
                                                                                         .setDate( "2020-01-01" ) ) );
    
    //    
    final ClassificationGroup classificationGroupRoot = new ClassificationGroup().setClassificationGroupId( "01-00-00-00" )
                                                                                 .setClassificationGroupName( "Root" );
    final ClassificationGroup classificationGroupFirstNode = new ClassificationGroup().setClassificationGroupId( "01-01-00-00" )
                                                                                      .setClassificationGroupName( "First node" )
                                                                                      .linkAsChildToClassificationGroup( classificationGroupRoot );
    final ClassificationGroups classificationGroups = new ClassificationGroups().add( classificationGroupRoot )
                                                                                .add( classificationGroupFirstNode );
    final ClassificationSystemFeatureTemplates classificationSystemFeatureTemplates = new ClassificationSystemFeatureTemplates();
    classificationSystemFeatureTemplates.add( new ClassificationSystemFeatureTemplate().setId( "xyz" ).setName( "name xyz" ) )
                                        .add( new ClassificationSystemFeatureTemplate().setId( "abc" ).setName( "name abc" ) );
    final ClassificationSystem classificationSystem = new ClassificationSystem().setClassificationSystemName( "ECLASS-6.1" )
                                                                                .setClassificationSystemVersion( "6.1" )
                                                                                .setClassificationGroups( classificationGroups )
                                                                                .setClassificationSystemFeatureTemplates( classificationSystemFeatureTemplates );
    
    //
    final CatalogGroupSystem catalogGroupSystem = new CatalogGroupSystem().setGroupSystemId( "system id" )
                                                                          .setGroupSystemName( "group system name" );
    CatalogStructure catalogStructureRoot = new CatalogStructure().setGroupId( "01-00-00-00" )
                                                                  .linkAsRootToCatalogGroupSystem( catalogGroupSystem );
    
    //
    final TNewCatalog tNewCatalog = new TNewCatalog().setCatalogGroupSystem( catalogGroupSystem ).add( classificationSystem );
    
    //
    {
      //
      CatalogStructure catalogStructureNode = new CatalogStructure().setGroupId( "01-01-00-00" )
                                                                    .linkAsChildToCatalogStructure( catalogGroupSystem,
                                                                                                    catalogStructureRoot );
      
      {
        //
        CatalogStructure catalogStructure = new CatalogStructure().setGroupId( "01-01-01-00" )
                                                                  .linkAsChildToCatalogStructure( catalogGroupSystem,
                                                                                                  catalogStructureNode );
        
        tNewCatalog.add( new Article().setSupplierAid( "001" )
                                      .setArticleDetails( new ArticleDetails().setDescriptionShort( "short description 1" )
                                                                              .setDescriptionLong( "description long 1" )
                                                                              .setManufacturerName( "manufacturer 1" )
                                                                              .setErpGroupBuyer( "B2383304" )
                                                                              .setErpGroupSupplier( "S98093843" )
                                                                              .add( new SpecialTreatmentClass().setType( "type" )
                                                                                                               .setValue( "10" ) )
                                                                              .addKeyword( "keyword1" )
                                                                              .addKeyword( "keyword2" )
                                                                              .add( new BuyerAid().setValue( "buyeraid1" )
                                                                                                  .setType( "any type" ) )
                                                                              .setDeliveryTime( "555355.555555" ) )
                                      .add( new ArticleFeatures().setReferenceFeatureGroupId( "01-01-01-01" )
                                                                 .setReferenceFeatureSystemName( "ECLASS-5.1" )
                                                                 .add( new Feature().setName( "color" )
                                                                                    .addValue( "red" )
                                                                                    .addValue( "white" )
                                                                                    .setDescription( "feature description" )
                                                                                    .setOrder( "1" ) )
                                                                 .add( new Feature().setName( "diameter" )
                                                                                    .setUnit( "mm" )
                                                                                    .addValue( "1" )
                                                                                    .addValue( "3" )
                                                                                    .addValue( "6" ) ) )
                                      .add( new ArticleFeatures().linkToClassificationSystemAndGroup( classificationSystem,
                                                                                                      classificationGroupFirstNode ) )
                                      .add( new ArticlePriceDetails().add( new ArticlePrice() ) )
                                      .linkToCatalogStructure( tNewCatalog, catalogStructure ) )
                   .add( new Article().setSupplierAid( "002" )
                                      .setArticleDetails( new ArticleDetails().setDescriptionShort( "short description 2" )
                                                                              .setDescriptionLong( "description long 2" )
                                                                              .setManufacturerName( "manufacturer 2" ) )
                                      .add( new ArticlePriceDetails().add( new ArticlePrice() ) )
                                      .linkToCatalogStructure( tNewCatalog, catalogStructure ) )
                   .add( new Article().setSupplierAid( "003" )
                                      .setArticleDetails( new ArticleDetails().setDescriptionShort( "short description 3" )
                                                                              .setDescriptionLong( "description long 3" )
                                                                              .setManufacturerName( "manufacturer 3" ) )
                                      .add( new ArticlePriceDetails().add( new ArticlePrice() ) )
                                      .linkToCatalogStructure( tNewCatalog, catalogStructure ) )
                   .add( new Article().setSupplierAid( "003" )
                                      .setArticleDetails( new ArticleDetails().setDescriptionShort( "short description 4" )
                                                                              .setDescriptionLong( "description long 4" )
                                                                              .setManufacturerName( "manufacturer 4" ) )
                                      .add( new ArticlePriceDetails().add( new ArticlePrice() ) )
                                      .linkToCatalogStructure( tNewCatalog, catalogStructure ) );
        
        for ( long counter : new Range( 1, 100 ) )
        {
          tNewCatalog.add( new Article().setSupplierAid( "" + counter )
                                        .setArticleDetails( new ArticleDetails().setDescriptionShort( "short description "
                                                                                                          + counter )
                                                                                .setDescriptionLong( "description long "
                                                                                                         + counter )
                                                                                .setManufacturerName( "manufacturer " + counter ) )
                                        .add( new ArticlePriceDetails().add( new ArticlePrice() ) )
                                        .linkToCatalogStructure( tNewCatalog, catalogStructure ) );
        }
      }
    }
    
    //
    BMECat12 bmeCat12 = new BMECat12().setHeader( header ).setTNewCatalog( tNewCatalog );
    
    //
    Set<ConstraintViolation<BMECat12>> contraintViolationSet = BMECat12Manager.validate( bmeCat12 );
    assertNotNull( contraintViolationSet );
    System.out.println( contraintViolationSet );
    assertEquals( 0, contraintViolationSet.size() );
    
  }
  
  @Test
  public void testIteratorFactory()
  {
    //
    final DurationCapture durationCapture = DurationCapture.newInstance();
    final int numberTo = 100;
    
    //   
    BMECat12 bmeCat12 = null;
    final String intervalKeyGenerateModel = "Generate model";
    durationCapture.startTimeMeasurement( intervalKeyGenerateModel );
    {
      //
      final Header header = new Header().setBuyer( new Buyer().setAddress( new Address().setType( Address.Type.buyer )
                                                                                        .setName( "name" )
                                                                                        .setContact( "contact" ) )
                                                              .setBuyerId( new BuyerId().setType( BuyerId.Type.buyer_specific )
                                                                                        .setValue( "buyer id" ) )
                                                              .setBuyerName( "buyer name" ) )
                                        .setCatalog( new Catalog().setCatalogId( "Catalog id 01" )
                                                                  .setCatalogName( "Catalog 1" )
                                                                  .setCatalogVersion( "001.001" )
                                                                  .setCurrency( "EUR" )
                                                                  .setLanguage( "de" )
                                                                  .addTerritory( "sjflsjflkjskjflkjsf" )
                                                                  .add( new PriceFlag().setType( PriceFlag.Type.incl_packing )
                                                                                       .setValue( true ) )
                                                                  .setDateTime( new Catalog.DateTime().setDate( "2012-01-01" )
                                                                                                      .setTime( "08:00:00" )
                                                                                                      .setTimezone( "GMT +1:00" ) ) )
                                        .setSupplier( new Supplier().setSupplierName( "supplier" )
                                                                    .add( new SupplierId().setType( SupplierId.Type.buyer_specific )
                                                                                          .setValue( "supplier id" ) )
                                                                    .setAddress( new Address().setType( Address.Type.supplier )
                                                                                              .setCity( "city" )
                                                                                              .setName( "name" )
                                                                                              .setStreet( "street" ) ) );
      
      //
      final FeatureSystem featureSystem = new FeatureSystem().setFeatureSystemName( "feature system 1" )
                                                             .add( new FeatureGroup().setFeatureGroupId( "feature group id" )
                                                                                     .setFeatureGroupName( "feature group name" ) );
      
      //    
      final ClassificationGroup classificationGroupRoot = new ClassificationGroup().setClassificationGroupId( "01-00-00-00" )
                                                                                   .setClassificationGroupName( "Root" );
      final ClassificationGroup classificationGroupFirstNode = new ClassificationGroup().setClassificationGroupId( "01-01-00-00" )
                                                                                        .setClassificationGroupName( "First node" )
                                                                                        .linkAsChildToClassificationGroup( classificationGroupRoot );
      final ClassificationGroups classificationGroups = new ClassificationGroups().add( classificationGroupRoot )
                                                                                  .add( classificationGroupFirstNode );
      final ClassificationSystemFeatureTemplates classificationSystemFeatureTemplates = new ClassificationSystemFeatureTemplates();
      classificationSystemFeatureTemplates.add( new ClassificationSystemFeatureTemplate().setId( "xyz" ).setName( "name xyz" ) )
                                          .add( new ClassificationSystemFeatureTemplate().setId( "abc" ).setName( "name abc" ) );
      final ClassificationSystem classificationSystem = new ClassificationSystem().setClassificationSystemName( "ECLASS-6.1" )
                                                                                  .setClassificationSystemVersion( "6.1" )
                                                                                  .setClassificationGroups( classificationGroups )
                                                                                  .setClassificationSystemFeatureTemplates( classificationSystemFeatureTemplates );
      
      //
      final CatalogGroupSystem catalogGroupSystem = new CatalogGroupSystem().setGroupSystemId( "system id" )
                                                                            .setGroupSystemName( "group system name" );
      CatalogStructure catalogStructureRoot = new CatalogStructure().setGroupId( "01-00-00-00" )
                                                                    .linkAsRootToCatalogGroupSystem( catalogGroupSystem );
      
      //
      final TNewCatalog tNewCatalog = new TNewCatalog().setCatalogGroupSystem( catalogGroupSystem )
                                                       .add( classificationSystem )
                                                       .add( featureSystem );
      
      //
      {
        //
        CatalogStructure catalogStructureNode = new CatalogStructure().setGroupId( "01-01-00-00" )
                                                                      .linkAsChildToCatalogStructure( catalogGroupSystem,
                                                                                                      catalogStructureRoot );
        
        {
          //
          CatalogStructure catalogStructure = new CatalogStructure().setGroupId( "01-01-01-00" )
                                                                    .linkAsChildToCatalogStructure( catalogGroupSystem,
                                                                                                    catalogStructureNode );
          
          for ( long counter : new Range( 1, numberTo ) )
          {
            tNewCatalog.add( new Article().setSupplierAid( "" + counter )
                                          .setArticleDetails( new ArticleDetails().setDescriptionShort( "short description "
                                                                                                            + counter )
                                                                                  .setDescriptionLong( "description long "
                                                                                                           + counter )
                                                                                  .setManufacturerName( "manufacturer " + counter ) )
                                          .add( new ArticlePriceDetails().add( new ArticlePrice() ) )
                                          .linkToCatalogStructure( tNewCatalog, catalogStructure ) );
          }
        }
      }
      
      //
      bmeCat12 = new BMECat12().setHeader( header ).setTNewCatalog( tNewCatalog );
      
      //
      Set<ConstraintViolation<BMECat12>> contraintViolationSet = BMECat12Manager.validate( bmeCat12 );
      assertNotNull( contraintViolationSet );
      System.out.println( contraintViolationSet );
      assertEquals( 0, contraintViolationSet.size() );
      
    }
    durationCapture.stopTimeMeasurement( intervalKeyGenerateModel );
    
    //
    final ByteArrayContainer byteArrayContainer = new ByteArrayContainer();
    final OutputStream outputStream = byteArrayContainer.getOutputStream();
    BMECat12Manager.storeTo( outputStream, bmeCat12 );
    
    //
    final String intervalKeyParseInput = "Parse generated input";
    durationCapture.startTimeMeasurement( intervalKeyParseInput );
    {
      //
      final SFHeader sfHeader = BMECat12Manager.newStreamFactory( byteArrayContainer.getInputStream(),
                                                                  new ExceptionHandlerEPrintStackTrace() );
      assertNotNull( sfHeader.getHeader() );
      
      //
      final SFFeatureSystem sfFeatureSystem = sfHeader.featureSystem();
      Iterator<FeatureSystem> featureSystemIterator = sfFeatureSystem.newFeatureSystemIterator();
      assertNotNull( featureSystemIterator );
      assertTrue( featureSystemIterator.hasNext() );
      
      //
      final SFClassificationSystem sfClassificationSystem = sfFeatureSystem.classificationSystem();
      Iterator<ClassificationSystem> classificationSystemIterator = sfClassificationSystem.newClassificationSystemIterator();
      assertNotNull( classificationSystemIterator );
      assertTrue( classificationSystemIterator.hasNext() );
      
      //
      final SFCatalogGroupSystem sfCatalogGroupSystem = sfClassificationSystem.catalogGroupSystem();
      assertNotNull( sfCatalogGroupSystem.getCatalogGroupSystem() );
      
      //  
      final SFArticles sfArticles;
      final String intervalKeyArticles = "articles";
      durationCapture.startTimeMeasurement( intervalKeyArticles );
      {
        sfArticles = sfCatalogGroupSystem.articles();
        Iterator<Article> articleIterator = sfArticles.newArticleIterator();
        assertNotNull( articleIterator );
        List<Article> articleList = ListUtils.valueOf( articleIterator );
        assertEquals( numberTo, articleList.size() );
      }
      durationCapture.stopTimeMeasurement( intervalKeyArticles );
      
      //
      final SFArticleToCatalogGroupMap sfArticleToCatalogGroupMap = sfArticles.articleToCatalogGroupMap();
      Iterator<ArticleToCatalogGroupMap> articleToCatalogGroupMapIterator = sfArticleToCatalogGroupMap.newArticleToCatalogGroupMapIterator();
      assertNotNull( articleToCatalogGroupMapIterator );
      assertTrue( articleToCatalogGroupMapIterator.hasNext() );
      sfArticleToCatalogGroupMap.close();
    }
    durationCapture.stopTimeMeasurement( intervalKeyParseInput );
    
    //
    System.out.println( durationCapture.calculateIntervalStatisticLogMessage() );
  }
}
