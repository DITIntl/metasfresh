package org.adempiere.ad.service;

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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.util.Properties;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_Sequence;

/**
 * Service to access the actual {@link I_AD_Sequence} table. To generate sequences, use {@link de.metas.document.impl.DocumentNoBuilder}.
 *
 */
public interface ISequenceDAO extends ISingletonService
{
	I_AD_Sequence retrieveTableSequenceOrNull(final Properties ctx, final String tableName, final String trxName);

	I_AD_Sequence retrieveTableSequenceOrNull(final Properties ctx, final String tableName);

	ITableSequenceChecker createTableSequenceChecker(Properties ctx);

	/**
	 * Rename the sequence name when a given table name was changed
	 * 
	 * @param ctx
	 * @param tableNameOld
	 * @param tableNameNew
	 */
	void renameTableSequence(Properties ctx, String tableNameOld, String tableNameNew);
}
