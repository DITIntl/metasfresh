package de.metas.material.dispo.commons.candidate;

import org.adempiere.util.Check;

import de.metas.lang.RepoIdAware;
import de.metas.material.dispo.commons.repository.query.CandidatesQuery;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/*
 * #%L
 * metasfresh-material-dispo-commons
 * %%
 * Copyright (C) 2018 metas GmbH
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
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CandidateId implements RepoIdAware
{
	/**
	 * Use this constant in a {@link CandidatesQuery} to indicate that the ID shall not be considered.
	 */
	public static final CandidateId UNSPECIFIED = CandidateId.ofRepoId(IdConstants.UNSPECIFIED_REPO_ID);

	/**
	 * Use this constant in a {@link CandidatesQuery} to indicate that the ID be null (makes sense for parent-ID).
	 */
	public static final CandidateId NULL = CandidateId.ofRepoId(IdConstants.NULL_REPO_ID);

	int repoId;

	public static CandidateId ofRepoId(final int repoId)
	{
		return new CandidateId(repoId);
	}

	public static CandidateId ofRepoIdOrNull(final int repoId)
	{
		return repoId > 0 ? new CandidateId(repoId) : null;
	}

	private CandidateId(final int repoId)
	{
		this.repoId = Check.assumeGreaterThanZero(repoId, "repoId");
	}

	@Override
	public int getRepoId()
	{
		if (isUnspecified())
		{
			Check.fail("Illegal call of getRepoId() on the unspecified CandidateId instance-");
		}
		else if (isNull())
		{
			return -1;
		}

		return repoId;
	}

	public boolean isNull()
	{
		return repoId == IdConstants.NULL_REPO_ID;
	}

	public boolean isUnspecified()
	{
		return repoId == IdConstants.UNSPECIFIED_REPO_ID;
	}
}
