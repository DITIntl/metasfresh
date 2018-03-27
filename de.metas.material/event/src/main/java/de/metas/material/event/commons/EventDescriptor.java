package de.metas.material.event.commons;

import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.IClientOrgAware;

import lombok.NonNull;
import lombok.Value;

/*
 * #%L
 * metasfresh-material-event
 * %%
 * Copyright (C) 2017 metas GmbH
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
@Value
public class EventDescriptor
{
	/**
	 *
	 * @param clientOrgAware model which can be made into a {@link IClientOrgAware} via {@link InterfaceWrapperHelper#asColumnReferenceAwareOrNull(Object, Class)}.
	 * @return
	 */
	public static EventDescriptor createNew(final Object clientOrgAware)
	{
		final IClientOrgAware clientOrgAwareToUse = InterfaceWrapperHelper.asColumnReferenceAwareOrNull(clientOrgAware, IClientOrgAware.class);

		return new EventDescriptor(
				clientOrgAwareToUse.getAD_Client_ID(),
				clientOrgAwareToUse.getAD_Org_ID());
	}

	public static EventDescriptor ofClientAndOrg(final int adClientId, final int adOrgId)
	{
		return new EventDescriptor(adClientId, adOrgId);
	}

	int clientId;
	int orgId;

	private EventDescriptor(
			@NonNull final Integer clientId,
			@NonNull final Integer orgId)
	{
		this.clientId = clientId;
		this.orgId = orgId;
	}

	public EventDescriptor createNew()
	{
		return new EventDescriptor(clientId, orgId);
	}
}
