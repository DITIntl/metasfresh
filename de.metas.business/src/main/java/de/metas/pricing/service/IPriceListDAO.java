package de.metas.pricing.service;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.I_M_PriceList;
import org.compiere.model.I_M_PriceList_Version;
import org.compiere.model.I_M_PricingSystem;
import org.compiere.model.I_M_ProductPrice;

import de.metas.lang.SOTrx;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PriceListVersionId;
import de.metas.pricing.PricingSystemId;
import de.metas.product.ProductId;

public interface IPriceListDAO extends ISingletonService
{
	public static final int M_PricingSystem_ID_None = PricingSystemId.NONE.getRepoId();
	public static final int M_PriceList_ID_None = PriceListId.NONE.getRepoId();

	I_M_PricingSystem getPricingSystemById(PricingSystemId pricingSystemId);

	PricingSystemId getPricingSystemIdByValue(String value);

	I_M_PriceList getById(PriceListId priceListId);

	I_M_PriceList getById(int priceListId);

	I_M_PriceList_Version getPriceListVersionById(PriceListVersionId priceListVersionId);

	/**
	 * Retrieves <b>all</b> (including inactive) {@link I_M_ProductPrice} record of the given price list version
	 *
	 * @param plv
	 * @return iterator of {@link I_M_ProductPrice} ordered by SeqNo and Name
	 */
	Iterator<I_M_ProductPrice> retrieveAllProductPricesOrderedBySeqNOandProductName(I_M_PriceList_Version plv);

	/**
	 * Returns a list containing all the PO price lists for a given pricing system and a country.<br>
	 * The method returns both price lists with the given country and without any country. The price list
	 * which has a country (if any) is ordered first.
	 *
	 * @param pricingSystem
	 * @param countryId
	 * @param soTrx sales, purchase or null to return both
	 */
	Iterator<I_M_PriceList> retrievePriceLists(PricingSystemId pricingSystemId, int countryId, SOTrx soTrx);

	/**
	 * @return the price list for the given pricing system and location or <code>null</code>.
	 */
	I_M_PriceList retrievePriceListByPricingSyst(PricingSystemId pricingSystemId, I_C_BPartner_Location bpartnerLocation, SOTrx soTrx);

	/**
	 * Retrieves the plv for the given price list and date. Never returns <code>null</code>
	 *
	 * @param priceList
	 * @param date
	 * @param processed optional, can be <code>null</code>. Allow to filter by <code>I_M_PriceList.Processed</code>
	 */
	I_M_PriceList_Version retrievePriceListVersionOrNull(org.compiere.model.I_M_PriceList priceList, Date date, Boolean processed);

	/**
	 * Retrieves the plv for the given price list and date. Never returns <code>null</code>
	 *
	 * @param ctx
	 * @param priceListId
	 * @param date
	 * @param processed optional, can be <code>null</code>. Allow to filter by <code>I_M_PriceList.Processed</code>
	 */
	I_M_PriceList_Version retrievePriceListVersionOrNull(Properties ctx, PriceListId priceListId, Date date, Boolean processed);

	/**
	 * Retrieve the price list version that has <code>Processed='Y'</code> and and was valid before after the the given <code>plv</code>.
	 *
	 * @param plv
	 * @return
	 */
	I_M_PriceList_Version retrieveNextVersionOrNull(I_M_PriceList_Version plv);

	/**
	 * Retrieve the price list version that has <code>Processed='Y'</code> and and was valid before before the the given <code>plv</code> .
	 *
	 * @param plv
	 * @return
	 */
	I_M_PriceList_Version retrievePreviousVersionOrNull(I_M_PriceList_Version plv);

	/** @return next product price's MatchSeqNo */
	int retrieveNextMatchSeqNo(final I_M_ProductPrice productPrice);

	I_M_PriceList_Version retrievePriceListVersionWithExactValidDate(int priceListId, Date date);

	I_M_PriceList_Version retrieveLastCreatedPriceListVersion(int priceListId);

	String getPricingSystemName(final PricingSystemId pricingSystemId);

	String getPriceListName(final PriceListId priceListId);

	Set<Integer> retrieveCountryIdsByPricingSystem(final PricingSystemId pricingSystemId);

	Set<ProductId> retrieveHighPriceProducts(BigDecimal minimumPrice, LocalDate date);
}
