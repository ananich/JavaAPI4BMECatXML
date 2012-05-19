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

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.CatalogGroupSystem.CatalogStructure;
import org.omnaest.api4bmecatxml.bmecat12.model.BMECat12.TBase.ClassificationSystem.ClassificationGroups.ClassificationGroup;

/**
 * Model based on the BMECat 1.2.1 specification
 * 
 * @see BMECat12Manager
 * @author Omnaest
 */
@XmlRootElement(name = "BMECAT")
@XmlAccessorType(XmlAccessType.FIELD)
public class BMECat12
{
  /* ********************************************** Variables ********************************************** */
  @NotNull
  @Valid
  @XmlElement(name = "HEADER")
  private Header          header          = null;
  
  @Valid
  @XmlElement(name = "T_NEW_CATALOG")
  private TNewCatalog     tNewCatalog     = null;
  
  @Valid
  @XmlElement(name = "T_UPDATE_PRODUCTS")
  private TUpdateProducts tUpdateProducts = null;
  
  @Valid
  @XmlElement(name = "T_UPDATE_PRICES")
  private TUpdatePrices   tUpdatePrices   = null;
  
  /* ********************************************** Classes/Interfaces ********************************************** */
  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Header
  {
    /* ********************************************** Variables ********************************************** */
    
    @XmlElement(name = "GENERATOR_INFO")
    private String          generatorInfo = null;
    
    @NotNull
    @Valid
    @XmlElement(name = "CATALOG")
    private Catalog         catalog       = null;
    
    @NotNull
    @Valid
    @XmlElement(name = "BUYER")
    private Buyer           buyer         = null;
    
    @Valid
    @XmlElement(name = "AGREEMENT")
    private List<Agreement> agreementList = null;
    
    @NotNull
    @Valid
    @XmlElement(name = "SUPPLIER")
    private Supplier        supplier      = null;
    
    /* ********************************************** Classes/Interfaces ********************************************** */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Catalog
    {
      /* ********************************************** Variables ********************************************** */
      @NotNull
      @Size(max = 3)
      @XmlElement(name = "LANGUAGE")
      private String          language       = null;
      
      @NotNull
      @Size(max = 20)
      @XmlElement(name = "CATALOG_ID")
      private String          catalogId      = null;
      
      @NotNull
      @Size(max = 7)
      @XmlElement(name = "CATALOG_VERSION")
      private String          catalogVersion = null;
      
      @Size(max = 100)
      @XmlElement(name = "CATALOG_NAME")
      private String          catalogName    = null;
      
      @Valid
      @XmlElement(name = "DATETIME")
      private DateTime        dateTime       = null;
      
      @XmlElement(name = "TERRITORY")
      private List<String>    territoryList  = null;
      
      @Size(max = 3)
      @XmlElement(name = "CURRENCY")
      private String          currency       = null;
      
      @Size(max = 100)
      @XmlElement(name = "MIME_ROOT")
      private String          mimeRoot       = null;
      
      @Valid
      @XmlElement(name = "PRICE_FLAG")
      private List<PriceFlag> priceFlagList  = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class PriceFlag
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @XmlAttribute(name = "type")
        private Type    type  = null;
        
        @NotNull
        @XmlValue
        private Boolean value = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlType(name = "PriceFlagType")
        public static enum Type
        {
          incl_freight,
          incl_packing,
          incl_assurance,
          incl_duty
        }
        
        /* ********************************************** Methods ********************************************** */
        public Type getType()
        {
          return this.type;
        }
        
        public PriceFlag setType( Type type )
        {
          this.type = type;
          return this;
        }
        
        public Boolean getValue()
        {
          return this.value;
        }
        
        public PriceFlag setValue( boolean value )
        {
          this.value = value;
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "PriceFlag [type=" );
          builder.append( this.type );
          builder.append( ", value=" );
          builder.append( this.value );
          builder.append( "]" );
          return builder.toString();
        }
        
      }
      
      @XmlType(name = "CATALOGDATETIME")
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class DateTime
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @XmlElement(name = "DATE")
        private String date     = null;
        
        @XmlElement(name = "TIME")
        private String time     = null;
        
        @XmlElement(name = "TIMEZONE")
        private String timezone = null;
        
        /* ********************************************** Methods ********************************************** */
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "DateTime [date=" );
          builder.append( this.date );
          builder.append( ", time=" );
          builder.append( this.time );
          builder.append( ", timezone=" );
          builder.append( this.timezone );
          builder.append( "]" );
          return builder.toString();
        }
        
        public String getDate()
        {
          return this.date;
        }
        
        public DateTime setDate( String date )
        {
          this.date = date;
          return this;
        }
        
        public String getTime()
        {
          return this.time;
        }
        
        public DateTime setTime( String time )
        {
          this.time = time;
          return this;
        }
        
        public String getTimezone()
        {
          return this.timezone;
        }
        
        public DateTime setTimezone( String timezone )
        {
          this.timezone = timezone;
          return this;
        }
        
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "Catalog [language=" );
        builder.append( this.language );
        builder.append( ", catalogId=" );
        builder.append( this.catalogId );
        builder.append( ", catalogVersion=" );
        builder.append( this.catalogVersion );
        builder.append( ", catalogName=" );
        builder.append( this.catalogName );
        builder.append( ", dateTime=" );
        builder.append( this.dateTime );
        builder.append( ", territory=" );
        builder.append( this.territoryList );
        builder.append( ", currency=" );
        builder.append( this.currency );
        builder.append( ", mimeRoot=" );
        builder.append( this.mimeRoot );
        builder.append( ", priceFlagList=" );
        builder.append( this.priceFlagList );
        builder.append( "]" );
        return builder.toString();
      }
      
      public String getLanguage()
      {
        return this.language;
      }
      
      public Catalog setLanguage( String language )
      {
        this.language = language;
        return this;
      }
      
      public String getCatalogId()
      {
        return this.catalogId;
      }
      
      public Catalog setCatalogId( String catalogId )
      {
        this.catalogId = catalogId;
        return this;
      }
      
      public String getCatalogVersion()
      {
        return this.catalogVersion;
      }
      
      public Catalog setCatalogVersion( String catalogVersion )
      {
        this.catalogVersion = catalogVersion;
        return this;
      }
      
      public String getCatalogName()
      {
        return this.catalogName;
      }
      
      public Catalog setCatalogName( String catalogName )
      {
        this.catalogName = catalogName;
        return this;
      }
      
      public DateTime getDateTime()
      {
        return this.dateTime;
      }
      
      public Catalog setDateTime( DateTime dateTime )
      {
        this.dateTime = dateTime;
        return this;
      }
      
      public String getCurrency()
      {
        return this.currency;
      }
      
      public Catalog setCurrency( String currency )
      {
        this.currency = currency;
        return this;
      }
      
      public List<String> getTerritoryList()
      {
        if ( this.territoryList == null )
        {
          this.territoryList = new ArrayList<String>();
        }
        return this.territoryList;
      }
      
      public Catalog setTerritory( List<String> territoryList )
      {
        this.territoryList = territoryList;
        return this;
      }
      
      /**
       * @see #getTerritoryList()
       * @param territory
       * @return
       */
      public Catalog addTerritory( String territory )
      {
        //
        if ( territory != null )
        {
          this.getTerritoryList().add( territory );
        }
        
        //
        return this;
      }
      
      public String getMimeRoot()
      {
        return this.mimeRoot;
      }
      
      public Catalog setMimeRoot( String mimeRoot )
      {
        this.mimeRoot = mimeRoot;
        return this;
      }
      
      public List<PriceFlag> getPriceFlagList()
      {
        if ( this.priceFlagList == null )
        {
          this.priceFlagList = new ArrayList<BMECat12.Header.Catalog.PriceFlag>();
        }
        return this.priceFlagList;
      }
      
      public Catalog setPriceFlagList( List<PriceFlag> priceFlagList )
      {
        this.priceFlagList = priceFlagList;
        return this;
      }
      
      /**
       * @see #getPriceFlagList()
       * @param priceFlag
       *          {@link PriceFlag}
       * @return
       */
      public Catalog add( PriceFlag priceFlag )
      {
        if ( priceFlag != null )
        {
          this.getPriceFlagList().add( priceFlag );
        }
        return this;
      }
      
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Address
    {
      /* ********************************************** Variables ********************************************** */
      
      @NotNull
      @Size(max = 8)
      @XmlAttribute(name = "type")
      private String          type           = null;
      
      @Size(max = 50)
      @XmlElement(name = "NAME")
      private String          name           = null;
      
      @Size(max = 50)
      @XmlElement(name = "NAME2")
      private String          name2          = null;
      
      @Size(max = 50)
      @XmlElement(name = "CONTACT")
      private String          contact        = null;
      
      @Size(max = 50)
      @XmlElement(name = "STREET")
      private String          street         = null;
      
      @Size(max = 20)
      @XmlElement(name = "ZIP")
      private String          zip            = null;
      
      @Size(max = 20)
      @XmlElement(name = "BOXNO")
      private String          boxNo          = null;
      
      @Size(max = 20)
      @XmlElement(name = "ZIPBOX")
      private String          zipBox         = null;
      
      @Size(max = 50)
      @XmlElement(name = "CITY")
      private String          city           = null;
      
      @Size(max = 50)
      @XmlElement(name = "STATE")
      private String          state          = null;
      
      @Size(max = 50)
      @XmlElement(name = "COUNTRY")
      private String          country        = null;
      
      @Size(max = 30)
      @XmlElement(name = "PHONE")
      private String          phone          = null;
      
      @Size(max = 30)
      @XmlElement(name = "FAX")
      private String          fax            = null;
      
      @Size(max = 100)
      @XmlElement(name = "EMAIL")
      private String          email          = null;
      
      @Valid
      @XmlElement(name = "PUBLIC_KEY")
      private List<PublicKey> publicKeyList  = null;
      
      @Size(max = 100)
      @XmlElement(name = "URL")
      private String          url            = null;
      
      @Size(max = 250)
      @XmlElement(name = "ADDRESS_REMARKS")
      private String          addressRemarks = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      public static enum Type
      {
        buyer,
        supplier
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class PublicKey
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(max = 50)
        @XmlAttribute(name = "type")
        private String type  = null;
        
        @NotNull
        @Size(max = 64000)
        @XmlValue
        private String value = null;
        
        /* ********************************************** Methods ********************************************** */
        
        public String getType()
        {
          return this.type;
        }
        
        public PublicKey setType( String type )
        {
          this.type = type;
          return this;
        }
        
        public String getValue()
        {
          return this.value;
        }
        
        public PublicKey setValue( String value )
        {
          this.value = value;
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "PublicKey [type=" );
          builder.append( this.type );
          builder.append( ", value=" );
          builder.append( this.value );
          builder.append( "]" );
          return builder.toString();
        }
        
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "Address [type=" );
        builder.append( this.type );
        builder.append( ", name=" );
        builder.append( this.name );
        builder.append( ", name2=" );
        builder.append( this.name2 );
        builder.append( ", contact=" );
        builder.append( this.contact );
        builder.append( ", street=" );
        builder.append( this.street );
        builder.append( ", zip=" );
        builder.append( this.zip );
        builder.append( ", boxNo=" );
        builder.append( this.boxNo );
        builder.append( ", zipBox=" );
        builder.append( this.zipBox );
        builder.append( ", city=" );
        builder.append( this.city );
        builder.append( ", state=" );
        builder.append( this.state );
        builder.append( ", country=" );
        builder.append( this.country );
        builder.append( ", phone=" );
        builder.append( this.phone );
        builder.append( ", fax=" );
        builder.append( this.fax );
        builder.append( ", email=" );
        builder.append( this.email );
        builder.append( ", publicKeyList=" );
        builder.append( this.publicKeyList );
        builder.append( ", url=" );
        builder.append( this.url );
        builder.append( ", addressRemarks=" );
        builder.append( this.addressRemarks );
        builder.append( "]" );
        return builder.toString();
      }
      
      public String getName()
      {
        return this.name;
      }
      
      public String getContact()
      {
        return this.contact;
      }
      
      public Address setName( String name )
      {
        this.name = name;
        return this;
      }
      
      public Address setContact( String contact )
      {
        this.contact = contact;
        return this;
      }
      
      public String getName2()
      {
        return this.name2;
      }
      
      public Address setName2( String name2 )
      {
        this.name2 = name2;
        return this;
      }
      
      public String getStreet()
      {
        return this.street;
      }
      
      public Address setStreet( String street )
      {
        this.street = street;
        return this;
      }
      
      public String getZip()
      {
        return this.zip;
      }
      
      public Address setZip( String zip )
      {
        this.zip = zip;
        return this;
      }
      
      public String getBoxNo()
      {
        return this.boxNo;
      }
      
      public Address setBoxNo( String boxNo )
      {
        this.boxNo = boxNo;
        return this;
      }
      
      public String getZipBox()
      {
        return this.zipBox;
      }
      
      public Address setZipBox( String zipBox )
      {
        this.zipBox = zipBox;
        return this;
      }
      
      public String getCity()
      {
        return this.city;
      }
      
      public Address setCity( String city )
      {
        this.city = city;
        return this;
      }
      
      public String getState()
      {
        return this.state;
      }
      
      public Address setState( String state )
      {
        this.state = state;
        return this;
      }
      
      public String getCountry()
      {
        return this.country;
      }
      
      public Address setCountry( String country )
      {
        this.country = country;
        return this;
      }
      
      public String getPhone()
      {
        return this.phone;
      }
      
      public Address setPhone( String phone )
      {
        this.phone = phone;
        return this;
      }
      
      public String getFax()
      {
        return this.fax;
      }
      
      public Address setFax( String fax )
      {
        this.fax = fax;
        return this;
      }
      
      public String getEmail()
      {
        return this.email;
      }
      
      public Address setEmail( String email )
      {
        this.email = email;
        return this;
      }
      
      public String getUrl()
      {
        return this.url;
      }
      
      public Address setUrl( String url )
      {
        this.url = url;
        return this;
      }
      
      public String getAddressRemarks()
      {
        return this.addressRemarks;
      }
      
      public Address setAddressRemarks( String addressRemarks )
      {
        this.addressRemarks = addressRemarks;
        return this;
      }
      
      public String getType()
      {
        return this.type;
      }
      
      public Address setType( String type )
      {
        this.type = type;
        return this;
      }
      
      /**
       * @param type
       *          {@link Type}
       * @return
       */
      public Address setType( Type type )
      {
        this.type = type != null ? type.name() : null;
        return this;
      }
      
      public List<PublicKey> getPublicKeyList()
      {
        if ( this.publicKeyList == null )
        {
          this.publicKeyList = new ArrayList<BMECat12.Header.Address.PublicKey>();
        }
        return this.publicKeyList;
      }
      
      public Address setPublicKeyList( List<PublicKey> publicKeyList )
      {
        this.publicKeyList = publicKeyList;
        return this;
      }
      
      /**
       * @see #getPublicKeyList()
       * @param publicKey
       *          {@link PublicKey}
       * @return
       */
      public Address add( PublicKey publicKey )
      {
        if ( publicKey != null )
        {
          this.getPublicKeyList().add( publicKey );
        }
        return this;
      }
      
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Buyer
    {
      /* ********************************************** Variables ********************************************** */
      @Valid
      @XmlElement(name = "BUYER_ID")
      private BuyerId buyerId   = null;
      
      @NotNull
      @Size(max = 50)
      @XmlElement(name = "BUYER_NAME")
      private String  buyerName = null;
      
      @Valid
      @XmlElement(name = "ADDRESS")
      private Address address   = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class BuyerId
      {
        /* ********************************************** Variables ********************************************** */
        @Size(max = 50)
        @XmlAttribute(name = "type")
        private String type  = null;
        
        @Size(max = 50)
        @XmlValue
        private String value = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        public static enum Type
        {
          duns,
          iln,
          buyer_specific,
          supplier_specific
        }
        
        /* ********************************************** Methods ********************************************** */
        public String getType()
        {
          return this.type;
        }
        
        public BuyerId setType( String type )
        {
          this.type = type;
          return this;
        }
        
        /**
         * @param type
         *          {@link Type}
         * @return this
         */
        public BuyerId setType( Type type )
        {
          this.type = type != null ? type.name() : null;
          return this;
        }
        
        public String getValue()
        {
          return this.value;
        }
        
        public BuyerId setValue( String value )
        {
          this.value = value;
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "BuyerId [type=" );
          builder.append( this.type );
          builder.append( ", value=" );
          builder.append( this.value );
          builder.append( "]" );
          return builder.toString();
        }
        
      }
      
      /* ********************************************** Methods ********************************************** */
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "Buyer [buyerId=" );
        builder.append( this.buyerId );
        builder.append( ", buyerName=" );
        builder.append( this.buyerName );
        builder.append( ", address=" );
        builder.append( this.address );
        builder.append( "]" );
        return builder.toString();
      }
      
      public BuyerId getBuyerId()
      {
        return this.buyerId;
      }
      
      public String getBuyerName()
      {
        return this.buyerName;
      }
      
      public Address getAddress()
      {
        return this.address;
      }
      
      public Buyer setBuyerId( BuyerId buyerId )
      {
        this.buyerId = buyerId;
        return this;
      }
      
      public Buyer setBuyerName( String buyerName )
      {
        this.buyerName = buyerName;
        return this;
      }
      
      public Buyer setAddress( Address address )
      {
        this.address = address;
        return this;
      }
      
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Supplier
    {
      /* ********************************************** Variables ********************************************** */
      @Valid
      @XmlElement(name = "SUPPLIER_ID")
      private List<SupplierId> supplierIdList = null;
      
      @NotNull
      @Size(max = 50)
      @XmlElement(name = "SUPPLIER_NAME")
      private String           supplierName   = null;
      
      @Valid
      @XmlElement(name = "ADDRESS")
      private Address          address        = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class SupplierId
      {
        /* ********************************************** Variables ********************************************** */
        @Size(max = 50)
        @XmlAttribute(name = "type")
        private String type  = null;
        
        @Size(max = 50)
        @XmlValue
        private String value = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        public static enum Type
        {
          duns,
          iln,
          buyer_specific,
          supplier_specific
        }
        
        /* ********************************************** Methods ********************************************** */
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "SupplierId [type=" );
          builder.append( this.type );
          builder.append( ", value=" );
          builder.append( this.value );
          builder.append( "]" );
          return builder.toString();
        }
        
        public String getType()
        {
          return this.type;
        }
        
        public SupplierId setType( String type )
        {
          this.type = type;
          return this;
        }
        
        public SupplierId setType( Type type )
        {
          this.type = type != null ? type.name() : null;
          return this;
        }
        
        public String getValue()
        {
          return this.value;
        }
        
        public SupplierId setValue( String value )
        {
          this.value = value;
          return this;
        }
        
      }
      
      /* ********************************************** Methods ********************************************** */
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "Supplier [supplierIdList=" );
        builder.append( this.supplierIdList );
        builder.append( ", supplierName=" );
        builder.append( this.supplierName );
        builder.append( ", address=" );
        builder.append( this.address );
        builder.append( "]" );
        return builder.toString();
      }
      
      public String getSupplierName()
      {
        return this.supplierName;
      }
      
      public Supplier setSupplierName( String supplierName )
      {
        this.supplierName = supplierName;
        return this;
      }
      
      public Address getAddress()
      {
        return this.address;
      }
      
      public Supplier setAddress( Address address )
      {
        this.address = address;
        return this;
      }
      
      public List<SupplierId> getSupplierIdList()
      {
        if ( this.supplierIdList == null )
        {
          this.supplierIdList = new ArrayList<BMECat12.Header.Supplier.SupplierId>();
        }
        return this.supplierIdList;
      }
      
      public Supplier setSupplierIdList( List<SupplierId> supplierIdList )
      {
        this.supplierIdList = supplierIdList;
        return this;
      }
      
      /**
       * @see #getSupplierIdList()
       * @param supplierId
       *          {@link SupplierId}
       * @return
       */
      public Supplier add( SupplierId supplierId )
      {
        if ( supplierId != null )
        {
          this.getSupplierIdList().add( supplierId );
        }
        return this;
      }
      
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Agreement
    {
      /* ********************************************** Variables ********************************************** */
      @NotNull
      @Size(max = 50)
      @XmlElement(name = "AGREEMENT_ID")
      private String         agreementId  = null;
      
      @NotNull
      @Size(min = 1, max = 2)
      @XmlElement(name = "DATETIME")
      private List<DateTime> dateTimeList = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlType(name = "DATETIME")
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class DateTime
      {
        /* ********************************************** Variables ********************************************** */
        @XmlAttribute(name = "type")
        private String type = null;
        
        @XmlElement(name = "DATE")
        private String date = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlType(name = "DateTimeType")
        public static enum Type
        {
          agreement_start_date,
          agreement_end_date
        }
        
        /* ********************************************** Methods ********************************************** */
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "DateTime [type=" );
          builder.append( this.type );
          builder.append( ", date=" );
          builder.append( this.date );
          builder.append( "]" );
          return builder.toString();
        }
        
        public String getType()
        {
          return this.type;
        }
        
        public DateTime setType( String type )
        {
          this.type = type;
          return this;
        }
        
        public DateTime setType( Type type )
        {
          this.type = type != null ? type.name() : null;
          return this;
        }
        
        public String getDate()
        {
          return this.date;
        }
        
        public DateTime setDate( String date )
        {
          this.date = date;
          return this;
        }
        
      }
      
      /* ********************************************** Methods ********************************************** */
      public String getAgreementId()
      {
        return this.agreementId;
      }
      
      public Agreement setAgreementId( String agreementId )
      {
        this.agreementId = agreementId;
        return this;
      }
      
      public List<DateTime> getDateTimeList()
      {
        if ( this.dateTimeList == null )
        {
          this.dateTimeList = new ArrayList<BMECat12.Header.Agreement.DateTime>();
        }
        return this.dateTimeList;
      }
      
      public Agreement setDateTimeList( List<DateTime> dateTimeList )
      {
        this.dateTimeList = dateTimeList;
        return this;
      }
      
      /**
       * @see #getDateTimeList()
       * @param dateTime
       *          {@link DateTime}
       * @return this
       */
      public Agreement add( DateTime dateTime )
      {
        //
        if ( dateTime != null )
        {
          this.getDateTimeList().add( dateTime );
        }
        
        //
        return this;
      }
      
    }
    
    /* ********************************************** Methods ********************************************** */
    
    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append( "Header [generatorInfo=" );
      builder.append( this.generatorInfo );
      builder.append( ", catalog=" );
      builder.append( this.catalog );
      builder.append( ", buyer=" );
      builder.append( this.buyer );
      builder.append( ", agreementList=" );
      builder.append( this.agreementList );
      builder.append( ", supplier=" );
      builder.append( this.supplier );
      builder.append( "]" );
      return builder.toString();
    }
    
    public Catalog getCatalog()
    {
      return this.catalog;
    }
    
    public Buyer getBuyer()
    {
      return this.buyer;
    }
    
    public Supplier getSupplier()
    {
      return this.supplier;
    }
    
    public Header setCatalog( Catalog catalog )
    {
      this.catalog = catalog;
      return this;
    }
    
    public Header setBuyer( Buyer buyer )
    {
      this.buyer = buyer;
      return this;
    }
    
    public Header setSupplier( Supplier supplier )
    {
      this.supplier = supplier;
      return this;
    }
    
    public String getGeneratorInfo()
    {
      return this.generatorInfo;
    }
    
    public Header setGeneratorInfo( String generatorInfo )
    {
      this.generatorInfo = generatorInfo;
      return this;
    }
    
    public List<Agreement> getAgreementList()
    {
      if ( this.agreementList == null )
      {
        this.agreementList = new ArrayList<BMECat12.Header.Agreement>();
      }
      return this.agreementList;
    }
    
    public Header setAgreementList( List<Agreement> agreementList )
    {
      this.agreementList = agreementList;
      return this;
    }
    
    /**
     * @see #getAgreementList()
     * @param agreement
     *          {@link Agreement}
     * @return this
     */
    public Header add( Agreement agreement )
    {
      //
      if ( agreement != null )
      {
        this.getAgreementList().add( agreement );
      }
      
      //
      return this;
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class TBase
  {
    /* ********************************************** Classes/Interfaces ********************************************** */
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class FeatureSystem
    {
      @NotNull
      @Size(max = 50)
      @XmlElement(name = "")
      private String             featureSystemName        = null;
      
      @Size(max = 250)
      @XmlElement(name = "FEATURE_SYSTEM_DESCR")
      private String             featureSystemDescription = null;
      
      @NotNull
      @Size(min = 1)
      @Valid
      @XmlElement(name = "FEATURE_GROUP")
      private List<FeatureGroup> featureGroupList         = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class FeatureGroup
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(max = 20)
        @XmlElement(name = "FEATURE_GROUP_ID")
        private String                featureGroupId          = null;
        
        @NotNull
        @Size(max = 60)
        @XmlElement(name = "FEATURE_GROUP_NAME")
        private String                featureGroupName        = null;
        
        @Valid
        @XmlElement(name = "FEATURE_TEMPLATE")
        private List<FeatureTemplate> featureTemplateList     = null;
        
        @Size(max = 250)
        @XmlElement(name = "FEATURE_GROUP_DESCR")
        private String                featureGroupDescription = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class FeatureTemplate
        {
          /* ********************************************** Variables ********************************************** */
          @XmlAttribute(name = "type")
          private String type  = null;
          
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "FT_NAME")
          private String name  = null;
          
          @Size(max = 20)
          @XmlElement(name = "FT_UNIT")
          private String unit  = null;
          
          @Digits(integer = 10, fraction = 0)
          @XmlElement(name = "FT_ORDER")
          private String order = null;
          
          /* ********************************************** Methods ********************************************** */
          public String getType()
          {
            return this.type;
          }
          
          public FeatureTemplate setType( String type )
          {
            this.type = type;
            return this;
          }
          
          public String getName()
          {
            return this.name;
          }
          
          public FeatureTemplate setName( String name )
          {
            this.name = name;
            return this;
          }
          
          public String getUnit()
          {
            return this.unit;
          }
          
          public FeatureTemplate setUnit( String unit )
          {
            this.unit = unit;
            return this;
          }
          
          public String getOrder()
          {
            return this.order;
          }
          
          public FeatureTemplate setOrder( String order )
          {
            this.order = order;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "FeatureTemplate [type=" );
            builder.append( this.type );
            builder.append( ", name=" );
            builder.append( this.name );
            builder.append( ", unit=" );
            builder.append( this.unit );
            builder.append( ", order=" );
            builder.append( this.order );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        public String getFeatureGroupId()
        {
          return this.featureGroupId;
        }
        
        public FeatureGroup setFeatureGroupId( String featureGroupId )
        {
          this.featureGroupId = featureGroupId;
          return this;
        }
        
        public String getFeatureGroupName()
        {
          return this.featureGroupName;
        }
        
        public FeatureGroup setFeatureGroupName( String featureGroupName )
        {
          this.featureGroupName = featureGroupName;
          return this;
        }
        
        public List<FeatureTemplate> getFeatureTemplateList()
        {
          if ( this.featureTemplateList == null )
          {
            this.featureTemplateList = new ArrayList<BMECat12.TBase.FeatureSystem.FeatureGroup.FeatureTemplate>();
          }
          
          return this.featureTemplateList;
        }
        
        public FeatureGroup setFeatureTemplateList( List<FeatureTemplate> featureTemplateList )
        {
          this.featureTemplateList = featureTemplateList;
          return this;
        }
        
        /**
         * @param featureTemplate
         *          {@link FeatureTemplate}
         * @return this
         */
        public FeatureGroup add( FeatureTemplate featureTemplate )
        {
          if ( featureTemplate != null )
          {
            this.getFeatureTemplateList().add( featureTemplate );
          }
          return this;
        }
        
        public String getFeatureGroupDescription()
        {
          return this.featureGroupDescription;
        }
        
        public FeatureGroup setFeatureGroupDescription( String featureGroupDescription )
        {
          this.featureGroupDescription = featureGroupDescription;
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "FeatureGroup [featureGroupId=" );
          builder.append( this.featureGroupId );
          builder.append( ", featureGroupName=" );
          builder.append( this.featureGroupName );
          builder.append( ", featureTemplateList=" );
          builder.append( this.featureTemplateList );
          builder.append( ", featureGroupDescription=" );
          builder.append( this.featureGroupDescription );
          builder.append( "]" );
          return builder.toString();
        }
        
      }
      
      /* ********************************************** Methods ********************************************** */
      
      public String getFeatureSystemName()
      {
        return this.featureSystemName;
      }
      
      public FeatureSystem setFeatureSystemName( String featureSystemName )
      {
        this.featureSystemName = featureSystemName;
        return this;
      }
      
      public String getFeatureSystemDescription()
      {
        return this.featureSystemDescription;
      }
      
      public FeatureSystem setFeatureSystemDescription( String featureSystemDescription )
      {
        this.featureSystemDescription = featureSystemDescription;
        return this;
      }
      
      public List<FeatureGroup> getFeatureGroupList()
      {
        return this.featureGroupList;
      }
      
      public FeatureSystem setFeatureGroupList( List<FeatureGroup> featureGroupList )
      {
        this.featureGroupList = featureGroupList;
        return this;
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "FeatureSystem [featureSystemName=" );
        builder.append( this.featureSystemName );
        builder.append( ", featureSystemDescription=" );
        builder.append( this.featureSystemDescription );
        builder.append( ", featureGroupList=" );
        builder.append( this.featureGroupList );
        builder.append( "]" );
        return builder.toString();
      }
      
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ClassificationSystem
    {
      /* ********************************************** Variables ********************************************** */
      
      @NotNull
      @Size(max = 20)
      @XmlElement(name = "CLASSIFICATION_SYSTEM_NAME")
      private String                               classificationSystemName             = null;
      
      @Size(max = 60)
      @XmlElement(name = "CLASSIFICATION_SYSTEM_FULLNAME")
      private String                               classificationSystemFullName         = null;
      
      @Size(max = 20)
      @XmlElement(name = "CLASSIFICATION_SYSTEM_VERSION")
      private String                               classificationSystemVersion          = null;
      
      @Size(max = 250)
      @XmlElement(name = "CLASSIFICATION_SYSTEM_DESCR")
      private String                               classificationSystemDescription      = null;
      
      @Digits(integer = 10, fraction = 0)
      @XmlElement(name = "CLASSIFICATION_SYSTEM_LEVELS")
      private String                               classificationSystemLevels           = null;
      
      @Valid
      @XmlElement(name = "CLASSIFICATION_SYSTEM_LEVEL_NAMES")
      private ClassificationSystemLevelNames       classificationSystemLevelNames       = null;
      
      @Valid
      @XmlElement(name = "ALLOWED_VALUES")
      private AllowedValues                        allowedValues                        = null;
      
      @Valid
      @XmlElement(name = "UNITS")
      private Units                                units                                = null;
      
      @Valid
      @XmlElement(name = "CLASSIFICATION_SYSTEM_FEATURE_TEMPLATES")
      private ClassificationSystemFeatureTemplates classificationSystemFeatureTemplates = null;
      
      @Valid
      @XmlElement(name = "CLASSIFICATION_GROUPS")
      private ClassificationGroups                 classificationGroups                 = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ClassificationSystemLevelNames
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(min = 1)
        @Valid
        @XmlElement(name = "CLASSIFICATION_SYSTEM_LEVEL_NAME")
        private List<ClassificationSystemLevelName> classificationSystemLevelNameList = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class ClassificationSystemLevelName
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Digits(integer = 10, fraction = 0)
          @XmlAttribute(name = "level")
          private String level = null;
          
          @NotNull
          @Size(max = 250)
          @XmlValue
          private String value = null;
          
          /* ********************************************** Methods ********************************************** */
          public String getLevel()
          {
            return this.level;
          }
          
          public ClassificationSystemLevelName setLevel( String level )
          {
            this.level = level;
            return this;
          }
          
          public String getValue()
          {
            return this.value;
          }
          
          public ClassificationSystemLevelName setValue( String value )
          {
            this.value = value;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "ClassificationSystemLevelName [level=" );
            builder.append( this.level );
            builder.append( ", value=" );
            builder.append( this.value );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        
        public List<ClassificationSystemLevelName> getClassificationSystemLevelNameList()
        {
          if ( this.classificationSystemLevelNameList == null )
          {
            this.classificationSystemLevelNameList = new ArrayList<BMECat12.TBase.ClassificationSystem.ClassificationSystemLevelNames.ClassificationSystemLevelName>();
          }
          return this.classificationSystemLevelNameList;
        }
        
        public ClassificationSystemLevelNames setClassificationSystemLevelNameList( List<ClassificationSystemLevelName> classificationSystemLevelNameList )
        {
          this.classificationSystemLevelNameList = classificationSystemLevelNameList;
          return this;
        }
        
        /**
         * @param classificationSystemLevelName
         *          {@link ClassificationSystemLevelName}
         * @return this
         */
        public ClassificationSystemLevelNames add( ClassificationSystemLevelName classificationSystemLevelName )
        {
          if ( classificationSystemLevelName != null )
          {
            this.getClassificationSystemLevelNameList().add( classificationSystemLevelName );
          }
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ClassificationSystemLevelNames [classificationSystemLevelNameList=" );
          builder.append( this.classificationSystemLevelNameList );
          builder.append( "]" );
          return builder.toString();
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class AllowedValues
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(min = 1)
        @Valid
        @XmlElement(name = "ALLOWED_VALUE")
        private List<AllowedValue> allowedValueList = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class AllowedValue
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "ALLOWED_VALUE_ID")
          private String allowedValueId          = null;
          
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "ALLOWED_VALUE_NAME")
          private String allowedValueName        = null;
          
          @Size(max = 250)
          @XmlElement(name = "ALLOWED_VALUE_DESCR")
          private String allowedValueDescription = null;
          
          /* ********************************************** Methods ********************************************** */
          public String getAllowedValueId()
          {
            return this.allowedValueId;
          }
          
          public AllowedValue setAllowedValueId( String allowedValueId )
          {
            this.allowedValueId = allowedValueId;
            return this;
          }
          
          public String getAllowedValueName()
          {
            return this.allowedValueName;
          }
          
          public AllowedValue setAllowedValueName( String allowedValueName )
          {
            this.allowedValueName = allowedValueName;
            return this;
          }
          
          public String getAllowedValueDescription()
          {
            return this.allowedValueDescription;
          }
          
          public AllowedValue setAllowedValueDescription( String allowedValueDescription )
          {
            this.allowedValueDescription = allowedValueDescription;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "AllowedValue [allowedValueId=" );
            builder.append( this.allowedValueId );
            builder.append( ", allowedValueName=" );
            builder.append( this.allowedValueName );
            builder.append( ", allowedValueDescription=" );
            builder.append( this.allowedValueDescription );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        public List<AllowedValue> getAllowedValueList()
        {
          if ( this.allowedValueList == null )
          {
            this.allowedValueList = new ArrayList<BMECat12.TBase.ClassificationSystem.AllowedValues.AllowedValue>();
          }
          return this.allowedValueList;
        }
        
        public AllowedValues setAllowedValueList( List<AllowedValue> allowedValueList )
        {
          this.allowedValueList = allowedValueList;
          return this;
        }
        
        public AllowedValues add( AllowedValue allowedValue )
        {
          if ( allowedValue != null )
          {
            this.getAllowedValueList().add( allowedValue );
          }
          return this;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class Units
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(min = 1)
        @Valid
        @XmlElement(name = "UNIT")
        private List<Unit> unitList = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Unit
        {
          /* ********************************************** Variables ********************************************** */
          @Size(max = 20)
          @XmlAttribute(name = "system")
          private String system          = null;
          
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "UNIT_ID")
          private String unitId          = null;
          
          @Size(max = 60)
          @XmlElement(name = "UNIT_NAME")
          private String unitName        = null;
          
          @Size(max = 250)
          @XmlElement(name = "UNIT_DESCR")
          private String unitDescription = null;
          
          /* ********************************************** Methods ********************************************** */
          
          public String getSystem()
          {
            return this.system;
          }
          
          public Unit setSystem( String system )
          {
            this.system = system;
            return this;
          }
          
          public String getUnitId()
          {
            return this.unitId;
          }
          
          public Unit setUnitId( String unitId )
          {
            this.unitId = unitId;
            return this;
          }
          
          public String getUnitName()
          {
            return this.unitName;
          }
          
          public Unit setUnitName( String unitName )
          {
            this.unitName = unitName;
            return this;
          }
          
          public String getUnitDescription()
          {
            return this.unitDescription;
          }
          
          public Unit setUnitDescription( String unitDescription )
          {
            this.unitDescription = unitDescription;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "Unit [system=" );
            builder.append( this.system );
            builder.append( ", unitId=" );
            builder.append( this.unitId );
            builder.append( ", unitName=" );
            builder.append( this.unitName );
            builder.append( ", unitDescription=" );
            builder.append( this.unitDescription );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        
        public List<Unit> getUnitList()
        {
          if ( this.unitList == null )
          {
            this.unitList = new ArrayList<BMECat12.TBase.ClassificationSystem.Units.Unit>();
          }
          return this.unitList;
        }
        
        public Units setUnitList( List<Unit> unitList )
        {
          this.unitList = unitList;
          return this;
        }
        
        /**
         * @param unit
         *          {@link Unit}
         * @return this
         */
        public Units add( Unit unit )
        {
          if ( unit != null )
          {
            this.getUnitList().add( unit );
          }
          return this;
          
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "Units [unitList=" );
          builder.append( this.unitList );
          builder.append( "]" );
          return builder.toString();
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ClassificationSystemFeatureTemplates
      {
        /* ********************************************** Variables ********************************************** */
        
        @NotNull
        @Size(min = 1)
        @Valid
        @XmlElement(name = "CLASSIFICATION_SYSTEM_FEATURE_TEMPLATE")
        private List<ClassificationSystemFeatureTemplate> classificationSystemFeatureTemplateList = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class ClassificationSystemFeatureTemplate
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "FT_ID")
          private String id          = null;
          
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "FT_NAME")
          private String name        = null;
          
          @Size(max = 250)
          @XmlElement(name = "FT_DESCR")
          private String description = null;
          
          /* ********************************************** Methods ********************************************** */
          public String getId()
          {
            return this.id;
          }
          
          public ClassificationSystemFeatureTemplate setId( String id )
          {
            this.id = id;
            return this;
          }
          
          public String getName()
          {
            return this.name;
          }
          
          public ClassificationSystemFeatureTemplate setName( String name )
          {
            this.name = name;
            return this;
          }
          
          public String getDescription()
          {
            return this.description;
          }
          
          public ClassificationSystemFeatureTemplate setDescription( String description )
          {
            this.description = description;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "ClassificationSystemFeatureTemplate [id=" );
            builder.append( this.id );
            builder.append( ", name=" );
            builder.append( this.name );
            builder.append( ", description=" );
            builder.append( this.description );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        
        public List<ClassificationSystemFeatureTemplate> getClassificationSystemFeatureTemplateList()
        {
          if ( this.classificationSystemFeatureTemplateList == null )
          {
            this.classificationSystemFeatureTemplateList = new ArrayList<BMECat12.TBase.ClassificationSystem.ClassificationSystemFeatureTemplates.ClassificationSystemFeatureTemplate>();
          }
          return this.classificationSystemFeatureTemplateList;
        }
        
        public ClassificationSystemFeatureTemplates setClassificationSystemFeatureTemplateList( List<ClassificationSystemFeatureTemplate> classificationSystemFeatureTemplateList )
        {
          this.classificationSystemFeatureTemplateList = classificationSystemFeatureTemplateList;
          return this;
        }
        
        /**
         * @param classificationSystemFeatureTemplate
         *          {@link ClassificationSystemFeatureTemplate}
         * @return this
         */
        public ClassificationSystemFeatureTemplates add( ClassificationSystemFeatureTemplate classificationSystemFeatureTemplate )
        {
          if ( classificationSystemFeatureTemplate != null )
          {
            this.getClassificationSystemFeatureTemplateList().add( classificationSystemFeatureTemplate );
          }
          return this;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ClassificationGroups
      {
        /* ********************************************** Variables ********************************************** */
        
        @NotNull
        @Size(min = 1)
        @Valid
        @XmlElement(name = "CLASSIFICATION_GROUP")
        private List<ClassificationGroup> classificationGroupList = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class ClassificationGroup
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "CLASSIFICATION_GROUP_ID")
          private String                              classificationGroupId               = null;
          
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "CLASSIFICATION_GROUP_NAME")
          private String                              classificationGroupName             = null;
          
          @Size(max = 250)
          @XmlElement(name = "CLASSIFICATION_GROUP_DESCR")
          private String                              classificationGroupDescription      = null;
          
          @Valid
          @XmlElement(name = "CLASSIFICATION_GROUP_SYNONYMS")
          private ClassificationGroupSynonyms         classificationGroupSynonyms         = null;
          
          @Valid
          @XmlElement(name = "CLASSIFICATION_GROUP_FEATURE_TEMPLATES")
          private ClassificationGroupFeatureTemplates classificationGroupFeatureTemplates = null;
          
          @Size(max = 60)
          @XmlElement(name = "CLASSIFICATION_GROUP_PARENT_ID")
          private String                              classificationGroupParentId         = null;
          
          /* ********************************************** Classes/Interfaces ********************************************** */
          @XmlAccessorType(XmlAccessType.FIELD)
          public static class ClassificationGroupSynonyms
          {
            /* ********************************************** Variables ********************************************** */
            @NotNull
            @Size(min = 1)
            @XmlElement(name = "SYNONYM")
            private List<String> synonymList = null;
            
            /* ********************************************** Methods ********************************************** */
            public List<String> getSynonymList()
            {
              if ( this.synonymList == null )
              {
                this.synonymList = new ArrayList<String>();
              }
              
              return this.synonymList;
            }
            
            public ClassificationGroupSynonyms setSynonymList( List<String> synonymList )
            {
              this.synonymList = synonymList;
              return this;
            }
            
            /**
             * @param synonym
             * @return this
             */
            public ClassificationGroupSynonyms addSynonym( String synonym )
            {
              if ( synonym != null )
              {
                this.getSynonymList().add( synonym );
              }
              return this;
            }
            
            @Override
            public String toString()
            {
              StringBuilder builder = new StringBuilder();
              builder.append( "ClassificationGroupSynonyms [synonymList=" );
              builder.append( this.synonymList );
              builder.append( "]" );
              return builder.toString();
            }
            
          }
          
          @XmlAccessorType(XmlAccessType.FIELD)
          public static class ClassificationGroupFeatureTemplates
          {
            /* ********************************************** Variables ********************************************** */
            @NotNull
            @Size(min = 1)
            @Valid
            @XmlElement(name = "CLASSIFICATION_GROUP_FEATURE_TEMPLATE")
            private List<ClassificationGroupFeatureTemplate> classificationGroupFeatureTemplateList = null;
            
            /* ********************************************** Classes/Interfaces ********************************************** */
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class ClassificationGroupFeatureTemplate
            {
              /* ********************************************** Variables ********************************************** */
              
              @NotNull
              @Size(max = 60)
              @XmlElement(name = "FT_IDREF")
              private String        idRef         = null;
              
              @NotNull
              @XmlElement(name = "FT_MANDATORY")
              private Boolean       mandatory     = null;
              
              @NotNull
              @Size(max = 20)
              @XmlElement(name = "FT_DATATYPE")
              private String        datatype      = null;
              
              @Size(max = 60)
              @XmlElement(name = "FT_UNIT")
              private String        unit          = null;
              
              @Digits(integer = 10, fraction = 0)
              @XmlElement(name = "FT_ORDER")
              private String        order         = null;
              
              @Valid
              @XmlElement(name = "FT_ALLOWED_VALUES")
              private AllowedValues allowedValues = null;
              
              /* ********************************************** Classes/Interfaces ********************************************** */
              @XmlType(name = "ClassificationGroupFeatureTemplateAllowedValues")
              @XmlAccessorType(XmlAccessType.FIELD)
              public static class AllowedValues
              {
                /* ********************************************** Variables ********************************************** */
                @NotNull
                @Size(min = 1)
                @Valid
                @XmlElement(name = "ALLOWED_VALUE_IDREF")
                private List<AllowedValueIdRef> allowedValueIdRefs = null;
                
                /* ********************************************** Classes/Interfaces ********************************************** */
                
                @XmlAccessorType(XmlAccessType.FIELD)
                public static class AllowedValueIdRef
                {
                  /* ********************************************** Variables ********************************************** */
                  @Digits(integer = 10, fraction = 0)
                  @XmlAttribute(name = "order")
                  private String order = null;
                  
                  @NotNull
                  @Size(max = 60)
                  @XmlValue
                  private String value = null;
                  
                  /* ********************************************** Methods ********************************************** */
                  public String getOrder()
                  {
                    return this.order;
                  }
                  
                  public AllowedValueIdRef setOrder( String order )
                  {
                    this.order = order;
                    return this;
                  }
                  
                  public String getValue()
                  {
                    return this.value;
                  }
                  
                  public AllowedValueIdRef setValue( String value )
                  {
                    this.value = value;
                    return this;
                  }
                  
                }
                
                /* ********************************************** Methods ********************************************** */
                
                @Override
                public String toString()
                {
                  StringBuilder builder = new StringBuilder();
                  builder.append( "AllowedValues [allowedValueIdRefs=" );
                  builder.append( this.allowedValueIdRefs );
                  builder.append( "]" );
                  return builder.toString();
                }
                
                public List<AllowedValueIdRef> getAllowedValueIdRefs()
                {
                  if ( this.allowedValueIdRefs == null )
                  {
                    this.allowedValueIdRefs = new ArrayList<BMECat12.TBase.ClassificationSystem.ClassificationGroups.ClassificationGroup.ClassificationGroupFeatureTemplates.ClassificationGroupFeatureTemplate.AllowedValues.AllowedValueIdRef>();
                  }
                  return this.allowedValueIdRefs;
                }
                
                public AllowedValues setAllowedValueIdRefs( List<AllowedValueIdRef> allowedValueIdRefs )
                {
                  this.allowedValueIdRefs = allowedValueIdRefs;
                  return this;
                }
                
                /**
                 * @see #getAllowedValueIdRefs()
                 * @param allowedValueIdRef
                 *          {@link AllowedValueIdRef}
                 * @return this
                 */
                public AllowedValues addIdRef( AllowedValueIdRef allowedValueIdRef )
                {
                  if ( allowedValueIdRef != null )
                  {
                    this.getAllowedValueIdRefs().add( allowedValueIdRef );
                  }
                  return this;
                }
                
              }
              
              /* ********************************************** Methods ********************************************** */
              
              public String getIdRef()
              {
                return this.idRef;
              }
              
              public ClassificationGroupFeatureTemplate setIdRef( String idRef )
              {
                this.idRef = idRef;
                return this;
              }
              
              public Boolean getMandatory()
              {
                return this.mandatory;
              }
              
              public ClassificationGroupFeatureTemplate setMandatory( boolean mandatory )
              {
                this.mandatory = mandatory;
                return this;
              }
              
              public String getDatatype()
              {
                return this.datatype;
              }
              
              public ClassificationGroupFeatureTemplate setDatatype( String datatype )
              {
                this.datatype = datatype;
                return this;
              }
              
              public String getUnit()
              {
                return this.unit;
              }
              
              public ClassificationGroupFeatureTemplate setUnit( String unit )
              {
                this.unit = unit;
                return this;
              }
              
              public String getOrder()
              {
                return this.order;
              }
              
              public ClassificationGroupFeatureTemplate setOrder( String order )
              {
                this.order = order;
                return this;
              }
              
              public AllowedValues getAllowedValues()
              {
                return this.allowedValues;
              }
              
              public ClassificationGroupFeatureTemplate setAllowedValues( AllowedValues allowedValues )
              {
                this.allowedValues = allowedValues;
                return this;
              }
              
              @Override
              public String toString()
              {
                StringBuilder builder = new StringBuilder();
                builder.append( "ClassificationGroupFeatureTemplate [idRef=" );
                builder.append( this.idRef );
                builder.append( ", mandatory=" );
                builder.append( this.mandatory );
                builder.append( ", datatype=" );
                builder.append( this.datatype );
                builder.append( ", unit=" );
                builder.append( this.unit );
                builder.append( ", order=" );
                builder.append( this.order );
                builder.append( ", allowedValues=" );
                builder.append( this.allowedValues );
                builder.append( "]" );
                return builder.toString();
              }
              
            }
            
            /* ********************************************** Methods ********************************************** */
            
            public List<ClassificationGroupFeatureTemplate> getClassificationGroupFeatureTemplateList()
            {
              if ( this.classificationGroupFeatureTemplateList == null )
              {
                this.classificationGroupFeatureTemplateList = new ArrayList<BMECat12.TBase.ClassificationSystem.ClassificationGroups.ClassificationGroup.ClassificationGroupFeatureTemplates.ClassificationGroupFeatureTemplate>();
              }
              return this.classificationGroupFeatureTemplateList;
            }
            
            public ClassificationGroupFeatureTemplates setClassificationGroupFeatureTemplateList( List<ClassificationGroupFeatureTemplate> classificationGroupFeatureTemplateList )
            {
              this.classificationGroupFeatureTemplateList = classificationGroupFeatureTemplateList;
              return this;
            }
            
            /**
             * @param classificationGroupFeatureTemplate
             *          {@link ClassificationGroupFeatureTemplate}
             * @return this
             */
            public ClassificationGroupFeatureTemplates add( ClassificationGroupFeatureTemplate classificationGroupFeatureTemplate )
            {
              if ( classificationGroupFeatureTemplate != null )
              {
                this.getClassificationGroupFeatureTemplateList().add( classificationGroupFeatureTemplate );
              }
              return this;
            }
            
            @Override
            public String toString()
            {
              StringBuilder builder = new StringBuilder();
              builder.append( "ClassificationGroupFeatureTemplates [classificationGroupFeatureTemplateList=" );
              builder.append( this.classificationGroupFeatureTemplateList );
              builder.append( "]" );
              return builder.toString();
            }
            
          }
          
          /* ********************************************** Methods ********************************************** */
          
          public String getClassificationGroupId()
          {
            return this.classificationGroupId;
          }
          
          public ClassificationGroup setClassificationGroupId( String classificationGroupId )
          {
            this.classificationGroupId = classificationGroupId;
            return this;
          }
          
          public String getClassificationGroupName()
          {
            return this.classificationGroupName;
          }
          
          public ClassificationGroup setClassificationGroupName( String classificationGroupName )
          {
            this.classificationGroupName = classificationGroupName;
            return this;
          }
          
          public String getClassificationGroupDescription()
          {
            return this.classificationGroupDescription;
          }
          
          public ClassificationGroup setClassificationGroupDescription( String classificationGroupDescription )
          {
            this.classificationGroupDescription = classificationGroupDescription;
            return this;
          }
          
          public ClassificationGroupSynonyms getClassificationGroupSynonyms()
          {
            return this.classificationGroupSynonyms;
          }
          
          public ClassificationGroup setClassificationGroupSynonyms( ClassificationGroupSynonyms classificationGroupSynonyms )
          {
            this.classificationGroupSynonyms = classificationGroupSynonyms;
            return this;
          }
          
          public ClassificationGroupFeatureTemplates getClassificationGroupFeatureTemplates()
          {
            return this.classificationGroupFeatureTemplates;
          }
          
          public ClassificationGroup setClassificationGroupFeatureTemplates( ClassificationGroupFeatureTemplates classificationGroupFeatureTemplates )
          {
            this.classificationGroupFeatureTemplates = classificationGroupFeatureTemplates;
            return this;
          }
          
          public String getClassificationGroupParentId()
          {
            return this.classificationGroupParentId;
          }
          
          public ClassificationGroup setClassificationGroupParentId( String classificationGroupParentId )
          {
            this.classificationGroupParentId = classificationGroupParentId;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "ClassificationGroup [classificationGroupId=" );
            builder.append( this.classificationGroupId );
            builder.append( ", classificationGroupName=" );
            builder.append( this.classificationGroupName );
            builder.append( ", classificationGroupDescription=" );
            builder.append( this.classificationGroupDescription );
            builder.append( ", classificationGroupSynonyms=" );
            builder.append( this.classificationGroupSynonyms );
            builder.append( ", classificationGroupFeatureTemplates=" );
            builder.append( this.classificationGroupFeatureTemplates );
            builder.append( ", classificationGroupParentId=" );
            builder.append( this.classificationGroupParentId );
            builder.append( "]" );
            return builder.toString();
          }
          
          /**
           * @param classificationGroupParent
           *          {@link ClassificationGroup}
           * @return this
           */
          public ClassificationGroup linkAsChildToClassificationGroup( ClassificationGroup classificationGroupParent )
          {
            //
            if ( classificationGroupParent != null )
            {
              //
              final String classificationGroupId = classificationGroupParent.getClassificationGroupId();
              this.classificationGroupParentId = classificationGroupId;
            }
            
            // 
            return this;
          }
        }
        
        /* ********************************************** Methods ********************************************** */
        
        public List<ClassificationGroup> getClassificationGroupList()
        {
          if ( this.classificationGroupList == null )
          {
            this.classificationGroupList = new ArrayList<BMECat12.TBase.ClassificationSystem.ClassificationGroups.ClassificationGroup>();
          }
          return this.classificationGroupList;
        }
        
        public ClassificationGroups setClassificationGroupList( List<ClassificationGroup> classificationGroupList )
        {
          this.classificationGroupList = classificationGroupList;
          return this;
        }
        
        /**
         * @param classificationGroup
         *          {@link ClassificationGroup}
         * @return this
         */
        public ClassificationGroups add( ClassificationGroup classificationGroup )
        {
          if ( classificationGroup != null )
          {
            this.getClassificationGroupList().add( classificationGroup );
          }
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ClassificationGroups [classificationGroupList=" );
          builder.append( this.classificationGroupList );
          builder.append( "]" );
          return builder.toString();
        }
        
      }
      
      /* ********************************************** Methods ********************************************** */
      
      public String getClassificationSystemName()
      {
        return this.classificationSystemName;
      }
      
      public ClassificationSystem setClassificationSystemName( String classificationSystemName )
      {
        this.classificationSystemName = classificationSystemName;
        return this;
      }
      
      public String getClassificationSystemFullName()
      {
        return this.classificationSystemFullName;
      }
      
      public ClassificationSystem setClassificationSystemFullName( String classificationSystemFullName )
      {
        this.classificationSystemFullName = classificationSystemFullName;
        return this;
      }
      
      public String getClassificationSystemVersion()
      {
        return this.classificationSystemVersion;
      }
      
      public ClassificationSystem setClassificationSystemVersion( String classificationSystemVersion )
      {
        this.classificationSystemVersion = classificationSystemVersion;
        return this;
      }
      
      public String getClassificationSystemDescription()
      {
        return this.classificationSystemDescription;
      }
      
      public ClassificationSystem setClassificationSystemDescription( String classificationSystemDescription )
      {
        this.classificationSystemDescription = classificationSystemDescription;
        return this;
      }
      
      public String getClassificationSystemLevels()
      {
        return this.classificationSystemLevels;
      }
      
      public ClassificationSystem setClassificationSystemLevels( String classificationSystemLevels )
      {
        this.classificationSystemLevels = classificationSystemLevels;
        return this;
      }
      
      public ClassificationSystemLevelNames getClassificationSystemLevelNames()
      {
        return this.classificationSystemLevelNames;
      }
      
      public ClassificationSystem setClassificationSystemLevelNames( ClassificationSystemLevelNames classificationSystemLevelNames )
      {
        this.classificationSystemLevelNames = classificationSystemLevelNames;
        return this;
      }
      
      public AllowedValues getAllowedValues()
      {
        return this.allowedValues;
      }
      
      public ClassificationSystem setAllowedValues( AllowedValues allowedValues )
      {
        this.allowedValues = allowedValues;
        return this;
      }
      
      public Units getUnits()
      {
        return this.units;
      }
      
      public ClassificationSystem setUnits( Units units )
      {
        this.units = units;
        return this;
      }
      
      public ClassificationSystemFeatureTemplates getClassificationSystemFeatureTemplates()
      {
        return this.classificationSystemFeatureTemplates;
      }
      
      public ClassificationSystem setClassificationSystemFeatureTemplates( ClassificationSystemFeatureTemplates classificationSystemFeatureTemplates )
      {
        this.classificationSystemFeatureTemplates = classificationSystemFeatureTemplates;
        return this;
      }
      
      public ClassificationGroups getClassificationGroups()
      {
        return this.classificationGroups;
      }
      
      public ClassificationSystem setClassificationGroups( ClassificationGroups classificationGroups )
      {
        this.classificationGroups = classificationGroups;
        return this;
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "ClassificationSystem [classificationSystemName=" );
        builder.append( this.classificationSystemName );
        builder.append( ", classificationSystemFullName=" );
        builder.append( this.classificationSystemFullName );
        builder.append( ", classificationSystemVersion=" );
        builder.append( this.classificationSystemVersion );
        builder.append( ", classificationSystemDescription=" );
        builder.append( this.classificationSystemDescription );
        builder.append( ", classificationSystemLevels=" );
        builder.append( this.classificationSystemLevels );
        builder.append( ", classificationSystemLevelNames=" );
        builder.append( this.classificationSystemLevelNames );
        builder.append( ", allowedValues=" );
        builder.append( this.allowedValues );
        builder.append( ", units=" );
        builder.append( this.units );
        builder.append( ", classificationSystemFeatureTemplates=" );
        builder.append( this.classificationSystemFeatureTemplates );
        builder.append( ", classificationGroups=" );
        builder.append( this.classificationGroups );
        builder.append( "]" );
        return builder.toString();
      }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CatalogGroupSystem
    {
      /* ********************************************** Variables ********************************************** */
      @Size(max = 50)
      @XmlElement(name = "GROUP_SYSTEM_ID")
      private String                 groupSystemId          = null;
      
      @Size(max = 50)
      @XmlElement(name = "GROUP_SYSTEM_NAME")
      private String                 groupSystemName        = null;
      
      @NotNull
      @Size(min = 1)
      @XmlElement(name = "CATALOG_STRUCTURE")
      private List<CatalogStructure> catalogStructureList   = null;
      
      @Size(max = 250)
      @XmlElement(name = "GROUP_SYSTEM_DESCRIPTION")
      private String                 groupSystemDescription = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class CatalogStructure
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @XmlAttribute(name = "type")
        private Type           type                     = null;
        
        @NotNull
        @Size(max = 50)
        @XmlElement(name = "GROUP_ID")
        private String         groupId                  = null;
        
        @Size(max = 50)
        @XmlElement(name = "GROUP_NAME")
        private String         groupName                = null;
        
        @Size(max = 250)
        @XmlElement(name = "GROUP_DESCRIPTION")
        private String         groupDescription         = null;
        
        @NotNull
        @Size(max = 50)
        @XmlElement(name = "PARENT_ID")
        private String         parentId                 = null;
        
        @Digits(integer = 10, fraction = 0)
        @XmlElement(name = "GROUP_ORDER")
        private String         groupOrder               = null;
        
        @Valid
        @XmlElement(name = "MIME_INFO")
        private List<MIMEInfo> mimeInfoList             = null;
        
        @XmlAnyElement
        private List<Object>   userDefinedExtensionList = null;
        
        @XmlElement(name = "KEYWORD")
        private List<String>   keywordList              = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlType(name = "CatalogStructureType")
        public static enum Type
        {
          root,
          node,
          leaf
        }
        
        /* ********************************************** Methods ********************************************** */
        public Type getType()
        {
          return this.type;
        }
        
        /**
         * @param type
         *          {@link Type}
         * @return
         */
        public CatalogStructure setType( Type type )
        {
          this.type = type;
          return this;
        }
        
        public String getGroupId()
        {
          return this.groupId;
        }
        
        public CatalogStructure setGroupId( String groupId )
        {
          this.groupId = groupId;
          return this;
        }
        
        public String getGroupName()
        {
          return this.groupName;
        }
        
        public CatalogStructure setGroupName( String groupName )
        {
          this.groupName = groupName;
          return this;
        }
        
        public String getParentId()
        {
          return this.parentId;
        }
        
        public CatalogStructure setParentId( String parentId )
        {
          this.parentId = parentId;
          return this;
        }
        
        public String getGroupOrder()
        {
          return this.groupOrder;
        }
        
        public CatalogStructure setGroupOrder( String groupOrder )
        {
          this.groupOrder = groupOrder;
          return this;
        }
        
        public List<MIMEInfo> getMimeInfoList()
        {
          if ( this.mimeInfoList == null )
          {
            this.mimeInfoList = new ArrayList<BMECat12.TNewCatalog.MIMEInfo>();
          }
          return this.mimeInfoList;
        }
        
        public CatalogStructure setMimeInfoList( List<MIMEInfo> mimeInfoList )
        {
          this.mimeInfoList = mimeInfoList;
          return this;
        }
        
        public CatalogStructure add( MIMEInfo mimeInfo )
        {
          if ( mimeInfo != null )
          {
            this.getMimeInfoList().add( mimeInfo );
          }
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "CatalogStructure [type=" );
          builder.append( this.type );
          builder.append( ", groupId=" );
          builder.append( this.groupId );
          builder.append( ", groupName=" );
          builder.append( this.groupName );
          builder.append( ", groupDescription=" );
          builder.append( this.groupDescription );
          builder.append( ", parentId=" );
          builder.append( this.parentId );
          builder.append( ", groupOrder=" );
          builder.append( this.groupOrder );
          builder.append( ", mimeInfoList=" );
          builder.append( this.mimeInfoList );
          builder.append( ", userDefinedExtensionList=" );
          builder.append( this.userDefinedExtensionList );
          builder.append( ", keywordList=" );
          builder.append( this.keywordList );
          builder.append( "]" );
          return builder.toString();
        }
        
        /**
         * @param catalogGroupSystem
         *          {@link CatalogGroupSystem}
         * @param catalogStructureParent
         *          {@link CatalogStructure}
         * @return
         */
        public CatalogStructure linkAsChildToCatalogStructure( CatalogGroupSystem catalogGroupSystem,
                                                               CatalogStructure catalogStructureParent )
        {
          //
          if ( catalogGroupSystem != null && catalogStructureParent != null )
          {
            //
            catalogGroupSystem.add( this );
            this.parentId = catalogStructureParent.getGroupId();
            
            //
            if ( this.type == null )
            {
              this.setType( Type.leaf );
            }
            if ( catalogStructureParent.getType() == null || Type.leaf.name().equals( catalogStructureParent.getType() ) )
            {
              catalogStructureParent.setType( Type.node );
            }
          }
          
          //
          return this;
        }
        
        /**
         * @param catalogGroupSystem
         *          {@link CatalogGroupSystem}
         * @return
         */
        public CatalogStructure linkAsRootToCatalogGroupSystem( CatalogGroupSystem catalogGroupSystem )
        {
          //
          if ( catalogGroupSystem != null )
          {
            catalogGroupSystem.add( this );
            this.setType( Type.root );
          }
          
          //
          return this;
        }
        
        public String getGroupDescription()
        {
          return this.groupDescription;
        }
        
        public CatalogStructure setGroupDescription( String groupDescription )
        {
          this.groupDescription = groupDescription;
          return this;
        }
        
        public List<Object> getUserDefinedExtensionList()
        {
          return this.userDefinedExtensionList;
        }
        
        public CatalogStructure setUserDefinedExtensionList( List<Object> userDefinedExtensionList )
        {
          if ( this.userDefinedExtensionList == null )
          {
            this.userDefinedExtensionList = new ArrayList<Object>();
          }
          this.userDefinedExtensionList = userDefinedExtensionList;
          return this;
        }
        
        public List<String> getKeywordList()
        {
          if ( this.keywordList == null )
          {
            this.keywordList = new ArrayList<String>();
          }
          return this.keywordList;
        }
        
        public CatalogStructure setKeywordList( List<String> keywordList )
        {
          this.keywordList = keywordList;
          return this;
        }
        
        public CatalogStructure addKeyword( String keyword )
        {
          if ( keyword != null )
          {
            this.getKeywordList().add( keyword );
          }
          return this;
        }
      }
      
      /* ********************************************** Methods ********************************************** */
      
      public String getGroupSystemId()
      {
        return this.groupSystemId;
      }
      
      public CatalogGroupSystem setGroupSystemId( String groupSystemId )
      {
        this.groupSystemId = groupSystemId;
        return this;
      }
      
      public String getGroupSystemName()
      {
        return this.groupSystemName;
      }
      
      public CatalogGroupSystem setGroupSystemName( String groupSystemName )
      {
        this.groupSystemName = groupSystemName;
        return this;
      }
      
      public List<CatalogStructure> getCatalogStructureList()
      {
        if ( this.catalogStructureList == null )
        {
          this.catalogStructureList = new ArrayList<BMECat12.TNewCatalog.CatalogGroupSystem.CatalogStructure>();
        }
        return this.catalogStructureList;
      }
      
      public CatalogGroupSystem setCatalogStructureList( List<CatalogStructure> catalogStructureList )
      {
        this.catalogStructureList = catalogStructureList;
        return this;
      }
      
      /**
       * @param catalogStructure
       *          {@link CatalogStructure}
       * @return this
       */
      public CatalogGroupSystem add( CatalogStructure catalogStructure )
      {
        //
        if ( catalogStructure != null )
        {
          this.getCatalogStructureList().add( catalogStructure );
        }
        
        //
        return this;
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "CatalogGroupSystem [groupSystemId=" );
        builder.append( this.groupSystemId );
        builder.append( ", groupSystemName=" );
        builder.append( this.groupSystemName );
        builder.append( ", catalogStructureList=" );
        builder.append( this.catalogStructureList );
        builder.append( ", groupSystemDescription=" );
        builder.append( this.groupSystemDescription );
        builder.append( "]" );
        return builder.toString();
      }
      
      public String getGroupSystemDescription()
      {
        return this.groupSystemDescription;
      }
      
      public CatalogGroupSystem setGroupSystemDescription( String groupSystemDescription )
      {
        this.groupSystemDescription = groupSystemDescription;
        return this;
      }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Article
    {
      /* ********************************************** Variables ********************************************** */
      
      @XmlAttribute(name = "mode")
      private Mode                      mode                    = null;
      
      @Size(max = 32)
      @NotNull
      @XmlElement(name = "SUPPLIER_AID")
      private String                    supplierAid             = null;
      
      @Valid
      @XmlElement(name = "ARTICLE_DETAILS")
      private ArticleDetails            articleDetails          = null;
      
      @Valid
      @XmlElement(name = "ARTICLE_FEATURES")
      private List<ArticleFeatures>     articleFeaturesList     = null;
      
      @Valid
      @XmlElement(name = "ARTICLE_ORDER_DETAILS")
      private ArticleOrderDetails       articleOrderDetails     = null;
      
      @NotNull
      @Size(min = 1)
      @Valid
      @XmlElement(name = "ARTICLE_PRICE_DETAILS")
      private List<ArticlePriceDetails> articlePriceDetailsList = null;
      
      @Valid
      @XmlElement(name = "MIME_INFO")
      private MIMEInfo                  mimeInfo                = null;
      
      @Valid
      @XmlElement(name = "ARTICLE_REFERENCE")
      private List<ArticleReference>    articleReferenceList    = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ArticleDetails
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(max = 80)
        @XmlElement(name = "DESCRIPTION_SHORT")
        private String                      descriptionShort            = null;
        
        @Size(max = 64000)
        @XmlElement(name = "DESCRIPTION_LONG")
        private String                      descriptionLong             = null;
        
        @Size(max = 14)
        @XmlElement(name = "EAN")
        private String                      ean                         = null;
        
        @Size(max = 50)
        @XmlElement(name = "SUPLLIER_ALT_AID")
        private String                      supplierAltAid              = null;
        
        @Valid
        @XmlElement(name = "BUYER_AID")
        private List<BuyerAid>              buyerAidList                = null;
        
        @Size(max = 50)
        @XmlElement(name = "MANUFACTURER_AID")
        private String                      manufacturerAid             = null;
        
        @Size(max = 50)
        @XmlElement(name = "MANUFACTURER_NAME")
        private String                      manufacturerName            = null;
        
        @Size(max = 50)
        @XmlElement(name = "MANUFACTURER_TYPE_DESCR")
        private String                      manufacturerTypeDescription = null;
        
        @Size(max = 10)
        @XmlElement(name = "ERP_GROUP_BUYER")
        private String                      erpGroupBuyer               = null;
        
        @Size(max = 10)
        @XmlElement(name = "ERP_GOUP_SUPPLIER")
        private String                      erpGroupSupplier            = null;
        
        @Digits(integer = 6, fraction = 6)
        @XmlElement(name = "DELIVERY_TIME")
        private String                      deliveryTime                = null;
        
        @Valid
        @XmlElement(name = "SPECIAL_TREATMENT_CLASS")
        private List<SpecialTreatmentClass> specialTreatmentClassList   = null;
        
        @XmlElement(name = "KEYWORD")
        private List<String>                keywordList                 = null;
        
        @Size(max = 64000)
        @XmlElement(name = "REMARKS")
        private String                      remarks                     = null;
        
        @Size(max = 100)
        @XmlElement(name = "SEGMENT")
        private String                      segment                     = null;
        
        @Digits(integer = 10, fraction = 0)
        @XmlElement(name = "ARTICLE_ORDER")
        private String                      articleOrder                = null;
        
        @Valid
        @XmlElement(name = "ARTICLE_STATUS")
        private List<ArticleStatus>         articleStatusList           = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class SpecialTreatmentClass
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 50)
          @XmlAttribute(name = "type")
          private String type  = null;
          
          @Size(max = 20)
          @XmlValue
          private String value = null;
          
          /* ********************************************** Methods ********************************************** */
          
          public String getType()
          {
            return this.type;
          }
          
          public SpecialTreatmentClass setType( String type )
          {
            this.type = type;
            return this;
          }
          
          public String getValue()
          {
            return this.value;
          }
          
          public SpecialTreatmentClass setValue( String value )
          {
            this.value = value;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "SpecialTreatmentClass [type=" );
            builder.append( this.type );
            builder.append( ", value=" );
            builder.append( this.value );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class BuyerAid
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 50)
          @XmlAttribute(name = "type")
          private String type  = null;
          
          @Size(max = 50)
          @XmlValue
          private String value = null;
          
          /* ********************************************** Methods ********************************************** */
          public String getType()
          {
            return this.type;
          }
          
          public BuyerAid setType( String type )
          {
            this.type = type;
            return this;
          }
          
          public String getValue()
          {
            return this.value;
          }
          
          public BuyerAid setValue( String value )
          {
            this.value = value;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "BuyerAid [type=" );
            builder.append( this.type );
            builder.append( ", value=" );
            builder.append( this.value );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class ArticleStatus
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @XmlAttribute(name = "type")
          private Type   type  = null;
          
          @Size(max = 250)
          @XmlValue
          private String value = null;
          
          /* ********************************************** Classes/Interfaces ********************************************** */
          @XmlType(name = "ArticleStatusType")
          public static enum Type
          {
            Bargain,
            new_article,
            old_article,
            @XmlEnumValue("new")
            new_,
            used,
            refurbished,
            core_article,
            others
          }
          
          /* ********************************************** Methods ********************************************** */
          
          public Type getType()
          {
            return this.type;
          }
          
          public ArticleStatus setType( Type type )
          {
            this.type = type;
            return this;
          }
          
          public String getValue()
          {
            return this.value;
          }
          
          public ArticleStatus setValue( String value )
          {
            this.value = value;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "ArticleStatus [type=" );
            builder.append( this.type );
            builder.append( ", value=" );
            builder.append( this.value );
            builder.append( "]" );
            return builder.toString();
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        public String getDescriptionShort()
        {
          return this.descriptionShort;
        }
        
        public String getDescriptionLong()
        {
          return this.descriptionLong;
        }
        
        public String getManufacturerName()
        {
          return this.manufacturerName;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ArticleDetails [descriptionShort=" );
          builder.append( this.descriptionShort );
          builder.append( ", descriptionLong=" );
          builder.append( this.descriptionLong );
          builder.append( ", ean=" );
          builder.append( this.ean );
          builder.append( ", supplierAltAid=" );
          builder.append( this.supplierAltAid );
          builder.append( ", buyerAidList=" );
          builder.append( this.buyerAidList );
          builder.append( ", manufacturerAid=" );
          builder.append( this.manufacturerAid );
          builder.append( ", manufacturerName=" );
          builder.append( this.manufacturerName );
          builder.append( ", manufacturerTypeDescription=" );
          builder.append( this.manufacturerTypeDescription );
          builder.append( ", erpGroupBuyer=" );
          builder.append( this.erpGroupBuyer );
          builder.append( ", erpGroupSupplier=" );
          builder.append( this.erpGroupSupplier );
          builder.append( ", deliveryTime=" );
          builder.append( this.deliveryTime );
          builder.append( ", specialTreatmentClassList=" );
          builder.append( this.specialTreatmentClassList );
          builder.append( ", keywordList=" );
          builder.append( this.keywordList );
          builder.append( ", remarks=" );
          builder.append( this.remarks );
          builder.append( ", segment=" );
          builder.append( this.segment );
          builder.append( ", articleOrder=" );
          builder.append( this.articleOrder );
          builder.append( ", articleStatusList=" );
          builder.append( this.articleStatusList );
          builder.append( "]" );
          return builder.toString();
        }
        
        public ArticleDetails setDescriptionShort( String descriptionShort )
        {
          this.descriptionShort = descriptionShort;
          return this;
        }
        
        public ArticleDetails setDescriptionLong( String descriptionLong )
        {
          this.descriptionLong = descriptionLong;
          return this;
        }
        
        public ArticleDetails setManufacturerName( String manufacturerName )
        {
          this.manufacturerName = manufacturerName;
          return this;
        }
        
        public String getManufacturerTypeDescription()
        {
          return this.manufacturerTypeDescription;
        }
        
        public ArticleDetails setManufacturerTypeDescription( String manufacturerTypeDescription )
        {
          this.manufacturerTypeDescription = manufacturerTypeDescription;
          return this;
        }
        
        public String getEan()
        {
          return this.ean;
        }
        
        public ArticleDetails setEan( String ean )
        {
          this.ean = ean;
          return this;
        }
        
        public String getSupplierAltAid()
        {
          return this.supplierAltAid;
        }
        
        public ArticleDetails setSupplierAltAid( String supplierAltAid )
        {
          this.supplierAltAid = supplierAltAid;
          return this;
        }
        
        public String getManufacturerAid()
        {
          return this.manufacturerAid;
        }
        
        public ArticleDetails setManufacturerAid( String manufacturerAid )
        {
          this.manufacturerAid = manufacturerAid;
          return this;
        }
        
        public String getErpGroupBuyer()
        {
          return this.erpGroupBuyer;
        }
        
        public ArticleDetails setErpGroupBuyer( String erpGroupBuyer )
        {
          this.erpGroupBuyer = erpGroupBuyer;
          return this;
        }
        
        public String getErpGroupSupplier()
        {
          return this.erpGroupSupplier;
        }
        
        public ArticleDetails setErpGroupSupplier( String erpGroupSupplier )
        {
          this.erpGroupSupplier = erpGroupSupplier;
          return this;
        }
        
        public String getDeliveryTime()
        {
          return this.deliveryTime;
        }
        
        public ArticleDetails setDeliveryTime( String deliveryTime )
        {
          this.deliveryTime = deliveryTime;
          return this;
        }
        
        public String getRemarks()
        {
          return this.remarks;
        }
        
        public ArticleDetails setRemarks( String remarks )
        {
          this.remarks = remarks;
          return this;
        }
        
        public String getSegment()
        {
          return this.segment;
        }
        
        public ArticleDetails setSegment( String segment )
        {
          this.segment = segment;
          return this;
        }
        
        public String getArticleOrder()
        {
          return this.articleOrder;
        }
        
        public ArticleDetails setArticleOrder( String articleOrder )
        {
          this.articleOrder = articleOrder;
          return this;
        }
        
        public List<BuyerAid> getBuyerAidList()
        {
          if ( this.buyerAidList == null )
          {
            this.buyerAidList = new ArrayList<BMECat12.TNewCatalog.Article.ArticleDetails.BuyerAid>();
          }
          return this.buyerAidList;
        }
        
        public ArticleDetails setBuyerAidList( List<BuyerAid> buyerAidList )
        {
          this.buyerAidList = buyerAidList;
          return this;
        }
        
        /**
         * @see #getBuyerAidList()
         * @param buyerAid
         *          {@link BuyerAid}
         * @return
         */
        public ArticleDetails add( BuyerAid buyerAid )
        {
          //
          if ( buyerAid != null )
          {
            this.getBuyerAidList().add( buyerAid );
          }
          
          //
          return this;
        }
        
        public List<SpecialTreatmentClass> getSpecialTreatmentClassList()
        {
          if ( this.specialTreatmentClassList == null )
          {
            this.specialTreatmentClassList = new ArrayList<BMECat12.TNewCatalog.Article.ArticleDetails.SpecialTreatmentClass>();
          }
          return this.specialTreatmentClassList;
        }
        
        public ArticleDetails setSpecialTreatmentClassList( List<SpecialTreatmentClass> specialTreatmentClassList )
        {
          this.specialTreatmentClassList = specialTreatmentClassList;
          return this;
        }
        
        public List<String> getKeywordList()
        {
          if ( this.keywordList == null )
          {
            this.keywordList = new ArrayList<String>();
          }
          return this.keywordList;
        }
        
        public ArticleDetails setKeywordList( List<String> keywordList )
        {
          this.keywordList = keywordList;
          return this;
        }
        
        /**
         * @see #getSpecialTreatmentClassList()
         * @param specialTreatmentClass
         *          {@link SpecialTreatmentClass}
         * @return this
         */
        public ArticleDetails add( SpecialTreatmentClass specialTreatmentClass )
        {
          
          //
          if ( specialTreatmentClass != null )
          {
            this.getSpecialTreatmentClassList().add( specialTreatmentClass );
          }
          
          //
          return this;
        }
        
        /**
         * @see #getKeywordList()
         * @param keyword
         * @return this
         */
        public ArticleDetails addKeyword( String keyword )
        {
          //
          if ( keyword != null )
          {
            this.getKeywordList().add( keyword );
          }
          
          //
          return this;
        }
        
        public List<ArticleStatus> getArticleStatusList()
        {
          if ( this.articleStatusList == null )
          {
            this.articleStatusList = new ArrayList<BMECat12.TBase.Article.ArticleDetails.ArticleStatus>();
          }
          return this.articleStatusList;
        }
        
        public ArticleDetails setArticleStatusList( List<ArticleStatus> articleStatusList )
        {
          this.articleStatusList = articleStatusList;
          return this;
        }
        
        /**
         * @param articleStatus
         *          {@link ArticleStatus}
         * @return this
         */
        public ArticleDetails add( ArticleStatus articleStatus )
        {
          if ( articleStatus != null )
          {
            this.getArticleStatusList().add( articleStatus );
          }
          return this;
        }
        
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ArticleFeatures
      {
        /* ********************************************** Variables ********************************************** */
        @Size(max = 50)
        @XmlElement(name = "REFERENCE_FEATURE_SYSTEM_NAME")
        private String        referenceFeatureSystemName = null;
        
        @Size(max = 60)
        @XmlElement(name = "REFERENCE_FEATURE_GROUP_ID")
        private String        referenceFeatureGroupId    = null;
        
        @Size(max = 60)
        @XmlElement(name = "REFERENCE_FEATURE_GROUP_NAME")
        private String        referenceFeatureGroupName  = null;
        
        @Valid
        @XmlElement(name = "FEATURE")
        private List<Feature> featureList                = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Feature
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 60)
          @XmlElement(name = "FNAME")
          private String       name         = null;
          
          @XmlElement(name = "FVALUE")
          private List<String> valueList    = null;
          
          @XmlElement(name = "VARIANTS")
          private Variants     variants     = null;
          
          @Size(max = 20)
          @XmlElement(name = "FUNIT")
          private String       unit         = null;
          
          @Digits(integer = 10, fraction = 0)
          @XmlElement(name = "FORDER")
          private String       order        = null;
          
          @Size(max = 250)
          @XmlElement(name = "FDESCR")
          private String       description  = null;
          
          @Size(max = 250)
          @XmlElement(name = "FVALUE_DETAILS")
          private String       valueDetails = null;
          
          /* ********************************************** Classes/Interfaces ********************************************** */
          @XmlAccessorType(XmlAccessType.FIELD)
          public static class Variants
          {
            /* ********************************************** Variables ********************************************** */
            @NotNull
            @Size(min = 1)
            @Valid
            @XmlElement(name = "VARIANT")
            private List<Variant> variantList = null;
            
            @NotNull
            @Digits(integer = 10, fraction = 0)
            @XmlElement(name = "VORDER")
            private String        order       = null;
            
            /* ********************************************** Classes/Interfaces ********************************************** */
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Variant
            {
              /* ********************************************** Variables ********************************************** */
              @NotNull
              @Size(max = 60)
              @XmlElement(name = "FVALUE")
              private String value                 = null;
              
              @NotNull
              @Size(max = 32)
              @XmlElement(name = "SUPPLIER_AID_SUPPLEMENT")
              private String supplierAidSupplement = null;
              
              /* ********************************************** Methods ********************************************** */
              public String getValue()
              {
                return this.value;
              }
              
              public Variant setValue( String value )
              {
                this.value = value;
                return this;
              }
              
              public String getSupplierAidSupplement()
              {
                return this.supplierAidSupplement;
              }
              
              public Variant setSupplierAidSupplement( String supplierAidSupplement )
              {
                this.supplierAidSupplement = supplierAidSupplement;
                return this;
              }
              
              @Override
              public String toString()
              {
                StringBuilder builder = new StringBuilder();
                builder.append( "Variant [value=" );
                builder.append( this.value );
                builder.append( ", supplierAidSupplement=" );
                builder.append( this.supplierAidSupplement );
                builder.append( "]" );
                return builder.toString();
              }
              
            }
            
            /* ********************************************** Methods ********************************************** */
            public List<Variant> getVariantList()
            {
              if ( this.variantList == null )
              {
                this.variantList = new ArrayList<BMECat12.TBase.Article.ArticleFeatures.Feature.Variants.Variant>();
              }
              return this.variantList;
            }
            
            public Variants setVariantList( List<Variant> variantList )
            {
              this.variantList = variantList;
              return this;
            }
            
            /**
             * @param variant
             *          {@link Variant}
             * @return this
             */
            public Variants add( Variant variant )
            {
              if ( variant != null )
              {
                this.getVariantList().add( variant );
              }
              return this;
            }
            
            public String getOrder()
            {
              return this.order;
            }
            
            public Variants setOrder( String order )
            {
              this.order = order;
              return this;
            }
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "Feature [name=" );
            builder.append( this.name );
            builder.append( ", valueList=" );
            builder.append( this.valueList );
            builder.append( ", variants=" );
            builder.append( this.variants );
            builder.append( ", unit=" );
            builder.append( this.unit );
            builder.append( ", order=" );
            builder.append( this.order );
            builder.append( ", description=" );
            builder.append( this.description );
            builder.append( ", valueDetails=" );
            builder.append( this.valueDetails );
            builder.append( "]" );
            return builder.toString();
          }
          
          public String getName()
          {
            return this.name;
          }
          
          public Feature setName( String name )
          {
            this.name = name;
            return this;
          }
          
          public List<String> getValueList()
          {
            if ( this.valueList == null )
            {
              this.valueList = new ArrayList<String>();
            }
            return this.valueList;
          }
          
          public Feature setValueList( List<String> valueList )
          {
            this.valueList = valueList;
            return this;
          }
          
          /**
           * @see #getValueList()
           * @param value
           * @return
           */
          public Feature addValue( String value )
          {
            //
            if ( value != null )
            {
              this.getValueList().add( value );
            }
            
            //
            return this;
          }
          
          public String getUnit()
          {
            return this.unit;
          }
          
          public Feature setUnit( String unit )
          {
            this.unit = unit;
            return this;
          }
          
          public String getOrder()
          {
            return this.order;
          }
          
          public Feature setOrder( String order )
          {
            this.order = order;
            return this;
          }
          
          public String getDescription()
          {
            return this.description;
          }
          
          public Feature setDescription( String description )
          {
            this.description = description;
            return this;
          }
          
          public String getValueDetails()
          {
            return this.valueDetails;
          }
          
          public Feature setValueDetails( String valueDetails )
          {
            this.valueDetails = valueDetails;
            return this;
          }
          
          public Variants getVariants()
          {
            return this.variants;
          }
          
          public Feature setVariants( Variants variants )
          {
            this.variants = variants;
            return this;
          }
          
        }
        
        /* ********************************************** Methods ********************************************** */
        
        /**
         * @param classificationSystem
         *          {@link ClassificationSystem}
         * @param classificationGroup
         *          {@link ClassificationGroup}
         * @return this
         */
        public ArticleFeatures linkToClassificationSystemAndGroup( ClassificationSystem classificationSystem,
                                                                   ClassificationGroup classificationGroup )
        {
          //
          if ( classificationSystem != null && classificationGroup != null )
          {
            //
            this.referenceFeatureSystemName = classificationSystem.getClassificationSystemName();
            final String classificationGroupId = classificationGroup.getClassificationGroupId();
            if ( classificationGroupId != null )
            {
              //
              this.referenceFeatureGroupId = classificationGroupId;
            }
            else
            {
              //
              final String classificationGroupName = classificationGroup.getClassificationGroupName();
              this.referenceFeatureGroupName = classificationGroupName;
            }
          }
          
          //
          return this;
        }
        
        public String getReferenceFeatureSystemName()
        {
          return this.referenceFeatureSystemName;
        }
        
        public String getReferenceFeatureGroupId()
        {
          return this.referenceFeatureGroupId;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ArticleFeatures [referenceFeatureSystemName=" );
          builder.append( this.referenceFeatureSystemName );
          builder.append( ", referenceFeatureGroupId=" );
          builder.append( this.referenceFeatureGroupId );
          builder.append( ", referenceFeatureGroupName=" );
          builder.append( this.referenceFeatureGroupName );
          builder.append( ", featureList=" );
          builder.append( this.featureList );
          builder.append( "]" );
          return builder.toString();
        }
        
        public ArticleFeatures setReferenceFeatureSystemName( String referenceFeatureSystemName )
        {
          this.referenceFeatureSystemName = referenceFeatureSystemName;
          return this;
        }
        
        public ArticleFeatures setReferenceFeatureGroupId( String referenceFeatureGroupId )
        {
          this.referenceFeatureGroupId = referenceFeatureGroupId;
          return this;
        }
        
        public List<Feature> getFeatureList()
        {
          if ( this.featureList == null )
          {
            this.featureList = new ArrayList<BMECat12.TNewCatalog.Article.ArticleFeatures.Feature>();
          }
          return this.featureList;
        }
        
        public ArticleFeatures setFeatureList( List<Feature> featureList )
        {
          this.featureList = featureList;
          return this;
        }
        
        /**
         * @param feature
         *          {@link Feature}
         * @return
         */
        public ArticleFeatures add( Feature feature )
        {
          //
          if ( feature != null )
          {
            this.getFeatureList().add( feature );
          }
          
          //
          return this;
        }
        
        public String getReferenceFeatureGroupName()
        {
          return this.referenceFeatureGroupName;
        }
        
        public ArticleFeatures setReferenceFeatureGroupName( String referenceFeatureGroupName )
        {
          this.referenceFeatureGroupName = referenceFeatureGroupName;
          return this;
        }
        
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ArticleOrderDetails
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(max = 3)
        @XmlElement(name = "ORDER_UNIT")
        private String orderUnit                                 = null;
        
        @Size(max = 3)
        @XmlElement(name = "CONTENT_UNIT")
        private String contentUnit                               = null;
        
        @XmlElement(name = "NO_CU_PER_OU")
        private String numberOfcontentUnitsPerOrderUnitOfArticle = null;
        
        @Digits(integer = 10, fraction = 10)
        @XmlElement(name = "PRICE_QUANTITY")
        private String priceQuantity                             = null;
        
        @Digits(integer = 10, fraction = 10)
        @XmlElement(name = "QUANTITY_MIN")
        private String quantityMin                               = null;
        
        @Digits(integer = 10, fraction = 10)
        @XmlElement(name = "QUANTITY_INTERVAL")
        private String quantityInterval                          = null;
        
        /* ********************************************** Methods ********************************************** */
        public String getOrderUnit()
        {
          return this.orderUnit;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ArticleOrderDetails [orderUnit=" );
          builder.append( this.orderUnit );
          builder.append( ", contentUnit=" );
          builder.append( this.contentUnit );
          builder.append( ", numberOfcontentUnitsPerOrderUnitOfArticle=" );
          builder.append( this.numberOfcontentUnitsPerOrderUnitOfArticle );
          builder.append( ", priceQuantity=" );
          builder.append( this.priceQuantity );
          builder.append( ", quantityMin=" );
          builder.append( this.quantityMin );
          builder.append( ", quantityInterval=" );
          builder.append( this.quantityInterval );
          builder.append( "]" );
          return builder.toString();
        }
        
        public ArticleOrderDetails setOrderUnit( String orderUnit )
        {
          this.orderUnit = orderUnit;
          return this;
        }
        
        public String getContentUnit()
        {
          return this.contentUnit;
        }
        
        public ArticleOrderDetails setContentUnit( String contentUnit )
        {
          this.contentUnit = contentUnit;
          return this;
        }
        
        public String getNumberOfcontentUnitsPerOrderUnitOfArticle()
        {
          return this.numberOfcontentUnitsPerOrderUnitOfArticle;
        }
        
        public ArticleOrderDetails setNumberOfcontentUnitsPerOrderUnitOfArticle( String numberOfcontentUnitsPerOrderUnitOfArticle )
        {
          this.numberOfcontentUnitsPerOrderUnitOfArticle = numberOfcontentUnitsPerOrderUnitOfArticle;
          return this;
        }
        
        public String getPriceQuantity()
        {
          return this.priceQuantity;
        }
        
        public ArticleOrderDetails setPriceQuantity( String priceQuantity )
        {
          this.priceQuantity = priceQuantity;
          return this;
        }
        
        public String getQuantityMin()
        {
          return this.quantityMin;
        }
        
        public ArticleOrderDetails setQuantityMin( String quantityMin )
        {
          this.quantityMin = quantityMin;
          return this;
        }
        
        public String getQuantityInterval()
        {
          return this.quantityInterval;
        }
        
        public ArticleOrderDetails setQuantityInterval( String quantityInterval )
        {
          this.quantityInterval = quantityInterval;
          return this;
        }
        
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ArticlePriceDetails
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @Size(min = 1)
        @XmlElement(name = "ARTICLE_PRICE")
        private List<ArticlePrice> articlePriceList = null;
        
        @XmlElement(name = "DATETIME")
        private List<DateTime>     dateTimeList     = null;
        
        @XmlElement(name = "DAILY_PRICE")
        private Boolean            dailyPrice       = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class ArticlePrice
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @Size(max = 20)
          @XmlAttribute(name = "price_type")
          private String       priceType     = null;
          
          @NotNull
          @Digits(integer = 10, fraction = 10)
          @XmlElement(name = "PRICE_AMOUNT")
          private String       priceAmount   = null;
          
          @Size(max = 3)
          @XmlElement(name = "PRICE_CURRENCY")
          private String       priceCurrency = null;
          
          @Digits(integer = 10, fraction = 10)
          @XmlElement(name = "TAX")
          private String       tax           = null;
          
          @Digits(integer = 10, fraction = 10)
          @XmlElement(name = "PRICE_FACTOR")
          private String       priceFactor   = null;
          
          @Digits(integer = 10, fraction = 10)
          @XmlElement(name = "LOWER_BOUND")
          private String       lowerBound    = null;
          
          @XmlElement(name = "TERRITORY")
          private List<String> territoryList = null;
          
          /* ********************************************** Classes/Interfaces ********************************************** */
          
          public static enum PriceType
          {
            net_list,
            gros_list,
            net_customer,
            nrp,
            net_customer_exp
          }
          
          /* ********************************************** Methods ********************************************** */
          public String getPriceType()
          {
            return this.priceType;
          }
          
          public String getPriceAmount()
          {
            return this.priceAmount;
          }
          
          public String getPriceCurrency()
          {
            return this.priceCurrency;
          }
          
          public String getLowerBound()
          {
            return this.lowerBound;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "ArticlePrice [priceType=" );
            builder.append( this.priceType );
            builder.append( ", priceAmount=" );
            builder.append( this.priceAmount );
            builder.append( ", priceCurrency=" );
            builder.append( this.priceCurrency );
            builder.append( ", tax=" );
            builder.append( this.tax );
            builder.append( ", priceFactor=" );
            builder.append( this.priceFactor );
            builder.append( ", lowerBound=" );
            builder.append( this.lowerBound );
            builder.append( ", territoryList=" );
            builder.append( this.territoryList );
            builder.append( "]" );
            return builder.toString();
          }
          
          public ArticlePrice setPriceType( String priceType )
          {
            this.priceType = priceType;
            return this;
          }
          
          public ArticlePrice setPriceAmount( String priceAmount )
          {
            this.priceAmount = priceAmount;
            return this;
          }
          
          public ArticlePrice setPriceCurrency( String priceCurrency )
          {
            this.priceCurrency = priceCurrency;
            return this;
          }
          
          public ArticlePrice setLowerBound( String lowerBound )
          {
            this.lowerBound = lowerBound;
            return this;
          }
          
          public String getTax()
          {
            return this.tax;
          }
          
          public ArticlePrice setTax( String tax )
          {
            this.tax = tax;
            return this;
          }
          
          public String getPriceFactor()
          {
            return this.priceFactor;
          }
          
          public ArticlePrice setPriceFactor( String priceFactor )
          {
            this.priceFactor = priceFactor;
            return this;
          }
          
          public List<String> getTerritoryList()
          {
            if ( this.territoryList == null )
            {
              this.territoryList = new ArrayList<String>();
            }
            return this.territoryList;
          }
          
          public ArticlePrice setTerritoryList( List<String> territoryList )
          {
            this.territoryList = territoryList;
            return this;
          }
          
          /**
           * @see #getTerritoryList()
           * @param territory
           * @return this
           */
          public ArticlePrice addTerritory( String territory )
          {
            if ( territory != null )
            {
              this.getTerritoryList().add( territory );
            }
            return this;
          }
          
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class DateTime
        {
          /* ********************************************** Variables ********************************************** */
          @NotNull
          @XmlAttribute(name = "type")
          private Type   type  = null;
          
          @NotNull
          @XmlValue
          private String value = null;
          
          /* ********************************************** Classes/Interfaces ********************************************** */
          @XmlType(name = "ArticlePriceDateTimeType")
          public static enum Type
          {
            valid_start_date,
            valid_end_date
          }
          
          /* ********************************************** Classes/Interfaces ********************************************** */
          
          public Type getType()
          {
            return this.type;
          }
          
          public DateTime setType( Type type )
          {
            this.type = type;
            return this;
          }
          
          public String getValue()
          {
            return this.value;
          }
          
          public DateTime setValue( String value )
          {
            this.value = value;
            return this;
          }
          
          @Override
          public String toString()
          {
            StringBuilder builder = new StringBuilder();
            builder.append( "DateTime [type=" );
            builder.append( this.type );
            builder.append( ", value=" );
            builder.append( this.value );
            builder.append( "]" );
            return builder.toString();
          }
        }
        
        /* ********************************************** Methods ********************************************** */
        
        public List<ArticlePrice> getArticlePriceList()
        {
          if ( this.articlePriceList == null )
          {
            this.articlePriceList = new ArrayList<BMECat12.TNewCatalog.Article.ArticlePriceDetails.ArticlePrice>();
          }
          return this.articlePriceList;
        }
        
        /**
         * @see #getArticlePriceList()
         * @param articlePrice
         *          {@link ArticlePrice}
         * @return
         */
        public ArticlePriceDetails add( ArticlePrice articlePrice )
        {
          //
          if ( articlePrice != null )
          {
            this.getArticlePriceList().add( articlePrice );
          }
          
          //
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ArticlePriceDetails [articlePriceList=" );
          builder.append( this.articlePriceList );
          builder.append( ", dateTimeList=" );
          builder.append( this.dateTimeList );
          builder.append( ", dailyPrice=" );
          builder.append( this.dailyPrice );
          builder.append( "]" );
          return builder.toString();
        }
        
        public ArticlePriceDetails setArticlePriceList( List<ArticlePrice> articlePriceList )
        {
          this.articlePriceList = articlePriceList;
          return this;
        }
        
        public List<DateTime> getDateTimeList()
        {
          if ( this.dateTimeList == null )
          {
            this.dateTimeList = new ArrayList<BMECat12.TBase.Article.ArticlePriceDetails.DateTime>();
          }
          return this.dateTimeList;
        }
        
        public ArticlePriceDetails setDateTimeList( List<DateTime> dateTimeList )
        {
          this.dateTimeList = dateTimeList;
          return this;
        }
        
        /**
         * @param dateTime
         *          {@link DateTime}
         * @return this
         */
        public ArticlePriceDetails add( DateTime dateTime )
        {
          if ( dateTime != null )
          {
            this.getDateTimeList().add( dateTime );
          }
          return this;
        }
        
        public Boolean getDailyPrice()
        {
          return this.dailyPrice;
        }
        
        public ArticlePriceDetails setDailyPrice( boolean dailyPrice )
        {
          this.dailyPrice = dailyPrice ? true : null;
          return this;
        }
        
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class ArticleReference
      {
        /* ********************************************** Variables ********************************************** */
        @NotNull
        @XmlAttribute(name = "type")
        private Type   type           = null;
        
        @Digits(integer = 10, fraction = 0)
        @XmlAttribute(name = "quantity")
        private String quantity       = null;
        
        @NotNull
        @Size(max = 32)
        @XmlElement(name = "ART_ID_TO")
        private String articleIdTo    = null;
        
        @Size(max = 20)
        @XmlElement(name = "CATALOG_ID")
        private String catalogId      = null;
        
        @Size(max = 7)
        @XmlElement(name = "CATALOG_VERSION")
        private String catalogVersion = null;
        
        /* ********************************************** Classes/Interfaces ********************************************** */
        @XmlType(name = "ArticleReferenceType")
        public static enum Type
        {
          sparepart,
          similar,
          followup,
          mandatory,
          select,
          diff_orderunit,
          accessories,
          consists_of,
          others
        }
        
        /* ********************************************** Methods ********************************************** */
        public Type getType()
        {
          return this.type;
        }
        
        public ArticleReference setType( Type type )
        {
          this.type = type;
          return this;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "ArticleReference [type=" );
          builder.append( this.type );
          builder.append( ", quantity=" );
          builder.append( this.quantity );
          builder.append( ", articleIdTo=" );
          builder.append( this.articleIdTo );
          builder.append( ", catalogId=" );
          builder.append( this.catalogId );
          builder.append( ", catalogVersion=" );
          builder.append( this.catalogVersion );
          builder.append( "]" );
          return builder.toString();
        }
        
        public String getQuantity()
        {
          return this.quantity;
        }
        
        public ArticleReference setQuantity( String quantity )
        {
          this.quantity = quantity;
          return this;
        }
        
        public String getArticleIdTo()
        {
          return this.articleIdTo;
        }
        
        public ArticleReference setArticleIdTo( String articleIdTo )
        {
          this.articleIdTo = articleIdTo;
          return this;
        }
        
        public String getCatalogId()
        {
          return this.catalogId;
        }
        
        public ArticleReference setCatalogId( String catalogId )
        {
          this.catalogId = catalogId;
          return this;
        }
        
        public String getCatalogVersion()
        {
          return this.catalogVersion;
        }
        
        public ArticleReference setCatalogVersion( String catalogVersion )
        {
          this.catalogVersion = catalogVersion;
          return this;
        }
        
      }
      
      public static enum Mode
      {
        @XmlEnumValue("new")
        new_,
        update,
        delete
      }
      
      /* ********************************************** Methods ********************************************** */
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "Article [mode=" );
        builder.append( this.mode );
        builder.append( ", supplierAid=" );
        builder.append( this.supplierAid );
        builder.append( ", articleDetails=" );
        builder.append( this.articleDetails );
        builder.append( ", articleFeaturesList=" );
        builder.append( this.articleFeaturesList );
        builder.append( ", articleOrderDetails=" );
        builder.append( this.articleOrderDetails );
        builder.append( ", articlePriceDetailsList=" );
        builder.append( this.articlePriceDetailsList );
        builder.append( ", mimeInfo=" );
        builder.append( this.mimeInfo );
        builder.append( "]" );
        return builder.toString();
      }
      
      public String getSupplierAid()
      {
        return this.supplierAid;
      }
      
      public ArticleDetails getArticleDetails()
      {
        return this.articleDetails;
      }
      
      public List<ArticleFeatures> getArticleFeaturesList()
      {
        if ( this.articleFeaturesList == null )
        {
          this.articleFeaturesList = new ArrayList<BMECat12.TNewCatalog.Article.ArticleFeatures>();
        }
        return this.articleFeaturesList;
      }
      
      public ArticleOrderDetails getArticleOrderDetails()
      {
        return this.articleOrderDetails;
      }
      
      public MIMEInfo getMimeInfo()
      {
        return this.mimeInfo;
      }
      
      public Article setSupplierAid( String supplierAid )
      {
        this.supplierAid = supplierAid;
        return this;
      }
      
      public Article setArticleDetails( ArticleDetails articleDetails )
      {
        this.articleDetails = articleDetails;
        return this;
      }
      
      public Article setArticleOrderDetails( ArticleOrderDetails articleOrderDetails )
      {
        this.articleOrderDetails = articleOrderDetails;
        return this;
      }
      
      public Article setMimeInfo( MIMEInfo mimeInfo )
      {
        this.mimeInfo = mimeInfo;
        return this;
      }
      
      /**
       * @param tNewCatalog
       *          {@link TNewCatalog}
       * @param catalogStructure
       *          {@link CatalogStructure}
       * @return
       */
      public Article linkToCatalogStructure( TNewCatalog tNewCatalog, CatalogStructure catalogStructure )
      {
        //
        final String catalogGroupId = catalogStructure.getGroupId();
        final String articleId = this.getSupplierAid();
        tNewCatalog.add( new ArticleToCatalogGroupMap().setCatalogGroupId( catalogGroupId ).setArticleId( articleId ) );
        
        //
        return this;
      }
      
      public Article setArticleFeaturesList( List<ArticleFeatures> articleFeaturesList )
      {
        this.articleFeaturesList = articleFeaturesList;
        return this;
      }
      
      /**
       * @see #getArticleFeaturesList()
       * @param articleFeatures
       *          {@link ArticleFeatures}
       * @return
       */
      public Article add( ArticleFeatures articleFeatures )
      {
        //
        if ( articleFeatures != null )
        {
          this.getArticleFeaturesList().add( articleFeatures );
        }
        
        //
        return this;
      }
      
      public Mode getMode()
      {
        return this.mode;
      }
      
      public Article setMode( Mode mode )
      {
        this.mode = mode;
        return this;
      }
      
      public List<ArticlePriceDetails> getArticlePriceDetailsList()
      {
        if ( this.articlePriceDetailsList == null )
        {
          this.articlePriceDetailsList = new ArrayList<BMECat12.TBase.Article.ArticlePriceDetails>();
        }
        return this.articlePriceDetailsList;
      }
      
      public Article setArticlePriceDetailsList( List<ArticlePriceDetails> articlePriceDetailsList )
      {
        this.articlePriceDetailsList = articlePriceDetailsList;
        return this;
      }
      
      /**
       * @param articlePriceDetails
       *          {@link ArticlePriceDetails}
       * @return this
       */
      public Article add( ArticlePriceDetails articlePriceDetails )
      {
        if ( articlePriceDetails != null )
        {
          this.getArticlePriceDetailsList().add( articlePriceDetails );
        }
        return this;
      }
      
      public List<ArticleReference> getArticleReferenceList()
      {
        if ( this.articleReferenceList == null )
        {
          this.articleReferenceList = new ArrayList<BMECat12.TBase.Article.ArticleReference>();
        }
        return this.articleReferenceList;
      }
      
      public Article setArticleReferenceList( List<ArticleReference> articleReferenceList )
      {
        this.articleReferenceList = articleReferenceList;
        return this;
      }
      
      /**
       * @param articleReference
       *          {@link ArticleReference}
       * @return this
       */
      public Article add( ArticleReference articleReference )
      {
        if ( articleReference != null )
        {
          this.getArticleReferenceList().add( articleReference );
        }
        return this;
      }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ArticleToCatalogGroupMap
    {
      /* ********************************************** Variables ********************************************** */
      @Size(max = 6)
      @XmlAttribute(name = "mode")
      private String mode                          = null;
      
      @NotNull
      @Size(max = 32)
      @XmlElement(name = "ART_ID")
      private String articleId                     = null;
      
      @NotNull
      @Size(max = 50)
      @XmlElement(name = "CATALOG_GROUP_ID")
      private String catalogGroupId                = null;
      
      @Digits(integer = 10, fraction = 0)
      @XmlElement(name = "ARTICLE_TO_CATALOGGROUP_MAP_ORDER")
      private String articleToCatalogGroupMapOrder = null;
      
      /* ********************************************** Methods ********************************************** */
      public String getArticleId()
      {
        return this.articleId;
      }
      
      public ArticleToCatalogGroupMap setArticleId( String articleId )
      {
        this.articleId = articleId;
        return this;
      }
      
      public String getCatalogGroupId()
      {
        return this.catalogGroupId;
      }
      
      public ArticleToCatalogGroupMap setCatalogGroupId( String catalogGroupId )
      {
        this.catalogGroupId = catalogGroupId;
        return this;
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "ArticleToCatalogGroupMap [mode=" );
        builder.append( this.mode );
        builder.append( ", articleId=" );
        builder.append( this.articleId );
        builder.append( ", catalogGroupId=" );
        builder.append( this.catalogGroupId );
        builder.append( ", articleToCatalogGroupMapOrder=" );
        builder.append( this.articleToCatalogGroupMapOrder );
        builder.append( "]" );
        return builder.toString();
      }
      
      public String getArticleToCatalogGroupMapOrder()
      {
        return this.articleToCatalogGroupMapOrder;
      }
      
      public ArticleToCatalogGroupMap setArticleToCatalogGroupMapOrder( String articleToCatalogGroupMapOrder )
      {
        this.articleToCatalogGroupMapOrder = articleToCatalogGroupMapOrder;
        return this;
      }
      
      public String getMode()
      {
        return this.mode;
      }
      
      public ArticleToCatalogGroupMap setMode( String mode )
      {
        this.mode = mode;
        return this;
      }
      
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MIMEInfo
    {
      /* ********************************************** Variables ********************************************** */
      @Valid
      @NotNull
      @Size(min = 1)
      @XmlElement(name = "MIME")
      private List<MIME> mimeList = null;
      
      /* ********************************************** Classes/Interfaces ********************************************** */
      
      @XmlAccessorType(XmlAccessType.FIELD)
      public static class MIME
      {
        /* ********************************************** Variables ********************************************** */
        @Size(max = 30)
        @XmlElement(name = "MIME_TYPE")
        private String mimeType            = null;
        
        @NotNull
        @Size(max = 250)
        @XmlElement(name = "MIME_SOURCE")
        private String mimeSource          = null;
        
        @Size(max = 250)
        @XmlElement(name = "MIME_DESCR")
        private String mimeDescription     = null;
        
        @Size(max = 250)
        @XmlElement(name = "MIME_ALT")
        private String mimeAlternativeText = null;
        
        @Size(max = 20)
        @XmlElement(name = "MIME_PURPOSE")
        private String mimePurpose         = null;
        
        @Digits(integer = 10, fraction = 0)
        @XmlElement(name = "MIME_ORDER")
        private String mimeOrder           = null;
        
        /* ********************************************** Methods ********************************************** */
        
        public String getMimeType()
        {
          return this.mimeType;
        }
        
        public String getMimeSource()
        {
          return this.mimeSource;
        }
        
        public String getMimePurpose()
        {
          return this.mimePurpose;
        }
        
        public String getMimeOrder()
        {
          return this.mimeOrder;
        }
        
        @Override
        public String toString()
        {
          StringBuilder builder = new StringBuilder();
          builder.append( "MIME [mimeType=" );
          builder.append( this.mimeType );
          builder.append( ", mimeSource=" );
          builder.append( this.mimeSource );
          builder.append( ", mimeDescription=" );
          builder.append( this.mimeDescription );
          builder.append( ", mimeAlternative=" );
          builder.append( this.mimeAlternativeText );
          builder.append( ", mimePurpose=" );
          builder.append( this.mimePurpose );
          builder.append( ", mimeOrder=" );
          builder.append( this.mimeOrder );
          builder.append( "]" );
          return builder.toString();
        }
        
        public MIME setMimeType( String mimeType )
        {
          this.mimeType = mimeType;
          return this;
        }
        
        public MIME setMimeSource( String mimeSource )
        {
          this.mimeSource = mimeSource;
          return this;
        }
        
        public MIME setMimePurpose( String mimePurpose )
        {
          this.mimePurpose = mimePurpose;
          return this;
        }
        
        public MIME setMimeOrder( String mimeOrder )
        {
          this.mimeOrder = mimeOrder;
          return this;
        }
        
        public String getMimeDescription()
        {
          return this.mimeDescription;
        }
        
        public MIME setMimeDescription( String mimeDescription )
        {
          this.mimeDescription = mimeDescription;
          return this;
        }
        
        public String getMimeAlternativeText()
        {
          return this.mimeAlternativeText;
        }
        
        public MIME setMimeAlternativeText( String mimeAlternativeText )
        {
          this.mimeAlternativeText = mimeAlternativeText;
          return this;
        }
        
      }
      
      /* ********************************************** Methods ********************************************** */
      public List<MIME> getMimeList()
      {
        if ( this.mimeList == null )
        {
          this.mimeList = new ArrayList<BMECat12.TNewCatalog.MIMEInfo.MIME>();
        }
        return this.mimeList;
      }
      
      @Override
      public String toString()
      {
        StringBuilder builder = new StringBuilder();
        builder.append( "MIMEInfo [mimeList=" );
        builder.append( this.mimeList );
        builder.append( "]" );
        return builder.toString();
      }
      
      public MIMEInfo setMimeList( List<MIME> mimeList )
      {
        this.mimeList = mimeList;
        return this;
      }
      
      /**
       * @param mime
       * @return this
       */
      public MIMEInfo add( MIME mime )
      {
        //
        if ( mime != null )
        {
          this.getMimeList().add( mime );
        }
        
        //
        return this;
      }
      
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class TNewCatalog extends TBase
  {
    /* ********************************************** Variables ********************************************** */
    @Size(max = 5)
    @XmlAttribute(name = "prev_version")
    private String                         previousVersion              = null;
    
    @Valid
    @XmlElement(name = "FEATURE_SYSTEM")
    private List<FeatureSystem>            featureSystemList            = null;
    
    @Valid
    @XmlElement(name = "CLASSIFICATION_SYSTEM")
    private List<ClassificationSystem>     classificationSystemList     = null;
    
    @Valid
    @XmlElement(name = "CATALOG_GROUP_SYSTEM")
    private CatalogGroupSystem             catalogGroupSystem           = null;
    
    @NotNull
    @Size(min = 1)
    @Valid
    @XmlElement(name = "ARTICLE")
    private List<Article>                  articleList                  = null;
    
    @Valid
    @XmlElement(name = "ARTICLE_TO_CATALOGGROUP_MAP")
    private List<ArticleToCatalogGroupMap> articleToCatalogGroupMapList = null;
    
    /* ********************************************** Methods ********************************************** */
    
    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append( "TNewCatalog [previousVersion=" );
      builder.append( this.previousVersion );
      builder.append( ", featureSystemList=" );
      builder.append( this.featureSystemList );
      builder.append( ", classificationSystemList=" );
      builder.append( this.classificationSystemList );
      builder.append( ", catalogGroupSystem=" );
      builder.append( this.catalogGroupSystem );
      builder.append( ", articleList=" );
      builder.append( this.articleList );
      builder.append( ", articleToCatalogGroupMapList=" );
      builder.append( this.articleToCatalogGroupMapList );
      builder.append( "]" );
      return builder.toString();
    }
    
    public List<Article> getArticleList()
    {
      if ( this.articleList == null )
      {
        this.articleList = new ArrayList<Article>();
      }
      return this.articleList;
    }
    
    public TNewCatalog setArticleList( List<Article> articleList )
    {
      this.articleList = articleList;
      return this;
    }
    
    /**
     * @param article
     * @return this
     */
    public TNewCatalog add( Article article )
    {
      //
      if ( article != null )
      {
        this.getArticleList().add( article );
      }
      
      //
      return this;
    }
    
    public CatalogGroupSystem getCatalogGroupSystem()
    {
      return this.catalogGroupSystem;
    }
    
    public TNewCatalog setCatalogGroupSystem( CatalogGroupSystem catalogGroupSystem )
    {
      this.catalogGroupSystem = catalogGroupSystem;
      return this;
    }
    
    public List<ArticleToCatalogGroupMap> getArticleToCatalogGroupMapList()
    {
      if ( this.articleToCatalogGroupMapList == null )
      {
        this.articleToCatalogGroupMapList = new ArrayList<BMECat12.TNewCatalog.ArticleToCatalogGroupMap>();
      }
      return this.articleToCatalogGroupMapList;
    }
    
    public TNewCatalog setArticleToCatalogGroupMapList( List<ArticleToCatalogGroupMap> articleToCatalogGroupMapList )
    {
      this.articleToCatalogGroupMapList = articleToCatalogGroupMapList;
      return this;
    }
    
    /**
     * @param articleToCatalogGroupMap
     * @return this
     */
    public TNewCatalog add( ArticleToCatalogGroupMap articleToCatalogGroupMap )
    {
      //
      if ( articleToCatalogGroupMap != null )
      {
        this.getArticleToCatalogGroupMapList().add( articleToCatalogGroupMap );
      }
      
      //
      return this;
    }
    
    public String getPreviousVersion()
    {
      return this.previousVersion;
    }
    
    public TNewCatalog setPreviousVersion( String previousVersion )
    {
      this.previousVersion = previousVersion;
      return this;
    }
    
    public List<FeatureSystem> getFeatureSystemList()
    {
      if ( this.featureSystemList == null )
      {
        this.featureSystemList = new ArrayList<BMECat12.TNewCatalog.FeatureSystem>();
      }
      return this.featureSystemList;
    }
    
    public TNewCatalog setFeatureSystemList( List<FeatureSystem> featureSystemList )
    {
      this.featureSystemList = featureSystemList;
      return this;
    }
    
    /**
     * @param featureSystem
     *          {@link FeatureSystem}
     * @return
     */
    public TNewCatalog add( FeatureSystem featureSystem )
    {
      if ( featureSystem != null )
      {
        this.getFeatureSystemList().add( featureSystem );
      }
      return this;
    }
    
    public List<ClassificationSystem> getClassificationSystemList()
    {
      if ( this.classificationSystemList == null )
      {
        this.classificationSystemList = new ArrayList<BMECat12.TNewCatalog.ClassificationSystem>();
      }
      return this.classificationSystemList;
    }
    
    /**
     * @param classificationSystem
     *          {@link ClassificationSystem}
     * @return
     */
    public TNewCatalog add( ClassificationSystem classificationSystem )
    {
      if ( classificationSystem != null )
      {
        this.getClassificationSystemList().add( classificationSystem );
      }
      return this;
    }
    
    public TNewCatalog setClassificationSystemList( List<ClassificationSystem> classificationSystemList )
    {
      this.classificationSystemList = classificationSystemList;
      return this;
    }
    
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class TUpdateProducts extends TBase
  {
    /* ********************************************** Variables ********************************************** */
    @Size(max = 5)
    @XmlAttribute(name = "prev_version")
    private String                         previousVersion              = null;
    
    @NotNull
    @Size(min = 1)
    @Valid
    @XmlElement(name = "ARTICLE")
    private List<Article>                  articleList                  = null;
    
    @Valid
    @XmlElement(name = "ARTICLE_TO_CATALOGGROUP_MAP")
    private List<ArticleToCatalogGroupMap> articleToCatalogGroupMapList = null;
    
    /* ********************************************** Methods ********************************************** */
    public String getPreviousVersion()
    {
      return this.previousVersion;
    }
    
    public TUpdateProducts setPreviousVersion( String previousVersion )
    {
      this.previousVersion = previousVersion;
      return this;
    }
    
    public List<Article> getArticleList()
    {
      if ( this.articleList == null )
      {
        this.articleList = new ArrayList<BMECat12.TBase.Article>();
      }
      return this.articleList;
    }
    
    public TUpdateProducts setArticleList( List<Article> articleList )
    {
      this.articleList = articleList;
      return this;
    }
    
    /**
     * @param article
     *          {@link Article}
     * @return this
     */
    public TUpdateProducts add( Article article )
    {
      if ( article != null )
      {
        this.getArticleList().add( article );
      }
      return this;
    }
    
    public List<ArticleToCatalogGroupMap> getArticleToCatalogGroupMapList()
    {
      if ( this.articleToCatalogGroupMapList == null )
      {
        this.articleToCatalogGroupMapList = new ArrayList<BMECat12.TBase.ArticleToCatalogGroupMap>();
      }
      return this.articleToCatalogGroupMapList;
    }
    
    public TUpdateProducts setArticleToCatalogGroupMapList( List<ArticleToCatalogGroupMap> articleToCatalogGroupMapList )
    {
      this.articleToCatalogGroupMapList = articleToCatalogGroupMapList;
      return this;
    }
    
    /**
     * @param articleToCatalogGroupMap
     *          {@link ArticleToCatalogGroupMap}
     * @return this
     */
    public TUpdateProducts add( ArticleToCatalogGroupMap articleToCatalogGroupMap )
    {
      if ( articleToCatalogGroupMap != null )
      {
        this.getArticleToCatalogGroupMapList().add( articleToCatalogGroupMap );
      }
      return this;
    }
    
    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append( "TUpdateProducts [previousVersion=" );
      builder.append( this.previousVersion );
      builder.append( ", articleList=" );
      builder.append( this.articleList );
      builder.append( ", articleToCatalogGroupMapList=" );
      builder.append( this.articleToCatalogGroupMapList );
      builder.append( "]" );
      return builder.toString();
    }
    
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class TUpdatePrices extends TBase
  {
    /* ********************************************** Variables ********************************************** */
    @Size(max = 5)
    @XmlAttribute(name = "prev_version")
    private String        previousVersion = null;
    
    @NotNull
    @Size(min = 1)
    @Valid
    @XmlElement(name = "ARTICLE")
    private List<Article> articleList     = null;
    
    /* ********************************************** Methods ********************************************** */
    public String getPreviousVersion()
    {
      return this.previousVersion;
    }
    
    public TUpdatePrices setPreviousVersion( String previousVersion )
    {
      this.previousVersion = previousVersion;
      return this;
    }
    
    public List<Article> getArticleList()
    {
      if ( this.articleList == null )
      {
        this.articleList = new ArrayList<BMECat12.TBase.Article>();
      }
      return this.articleList;
    }
    
    public TUpdatePrices setArticleList( List<Article> articleList )
    {
      this.articleList = articleList;
      return this;
    }
    
    /**
     * @param article
     *          {@link Article}
     * @return this
     */
    public TUpdatePrices add( Article article )
    {
      if ( article != null )
      {
        this.getArticleList().add( article );
      }
      return this;
    }
    
    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append( "TUpdatePrices [previousVersion=" );
      builder.append( this.previousVersion );
      builder.append( ", articleList=" );
      builder.append( this.articleList );
      builder.append( "]" );
      return builder.toString();
    }
    
  }
  
  /* ********************************************** Methods ********************************************** */
  public Header getHeader()
  {
    return this.header;
  }
  
  public BMECat12 setHeader( Header header )
  {
    this.header = header;
    return this;
  }
  
  public TNewCatalog getTNewCatalog()
  {
    return this.tNewCatalog;
  }
  
  public BMECat12 setTNewCatalog( TNewCatalog tNewCatalog )
  {
    this.tNewCatalog = tNewCatalog;
    return this;
  }
  
  public TUpdateProducts getTUpdateProducts()
  {
    return this.tUpdateProducts;
  }
  
  public BMECat12 setTUpdateProducts( TUpdateProducts tUpdateProducts )
  {
    this.tUpdateProducts = tUpdateProducts;
    return this;
  }
  
  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append( "BMECat12 [header=" );
    builder.append( this.header );
    builder.append( ", tNewCatalog=" );
    builder.append( this.tNewCatalog );
    builder.append( ", tUpdateProducts=" );
    builder.append( this.tUpdateProducts );
    builder.append( ", tUpdatePrices=" );
    builder.append( this.tUpdatePrices );
    builder.append( "]" );
    return builder.toString();
  }
  
  public TUpdatePrices getTUpdatePrices()
  {
    return this.tUpdatePrices;
  }
  
  public BMECat12 setTUpdatePrices( TUpdatePrices tUpdatePrices )
  {
    this.tUpdatePrices = tUpdatePrices;
    return this;
  }
}
