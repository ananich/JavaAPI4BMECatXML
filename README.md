JavaAPI4BMECatXML
=================

Java API for on the BMECat specification based XML content using JAXB and JSR-303 Validation API

Simple code example:

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


	BMECat12Manager.storeTo( ... , bmeCat12 )
	
will produce following XML:
	
	<?xml version="1.0" encoding="utf-8" standalone="yes"?>
	<BMECAT xmlns="http://www.bmecat.org/bmecat/1.2/bmecat_new_catalog">
		<HEADER>
			<CATALOG>
				<LANGUAGE>de</LANGUAGE>
				<CATALOG_ID>Example catalog id</CATALOG_ID>
				<CATALOG_VERSION>001.001</CATALOG_VERSION>
				<CATALOG_NAME>Example catalog</CATALOG_NAME>
			</CATALOG>
			<BUYER>
				<BUYER_NAME>Buyer 1</BUYER_NAME>
			</BUYER>
			<SUPPLIER>
				<SUPPLIER_NAME>Supplier 1</SUPPLIER_NAME>
			</SUPPLIER>
		</HEADER>
		<T_NEW_CATALOG>
			<ARTICLE>
				<SUPPLIER_AID>supplier 1</SUPPLIER_AID>
				<ARTICLE_PRICE_DETAILS>
					<ARTICLE_PRICE/>
				</ARTICLE_PRICE_DETAILS>
			</ARTICLE>
			<ARTICLE>
				<SUPPLIER_AID>supplier 1</SUPPLIER_AID>
				<ARTICLE_PRICE_DETAILS>
					<ARTICLE_PRICE/>
				</ARTICLE_PRICE_DETAILS>
			</ARTICLE>
			<ARTICLE>
				<SUPPLIER_AID>supplier 1</SUPPLIER_AID>
				<ARTICLE_PRICE_DETAILS>
					<ARTICLE_PRICE/>
				</ARTICLE_PRICE_DETAILS>
			</ARTICLE>
		</T_NEW_CATALOG>
	</BMECAT>