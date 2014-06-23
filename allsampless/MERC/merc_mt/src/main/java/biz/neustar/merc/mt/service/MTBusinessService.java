/**
 * 
 */
package biz.neustar.merc.mt.service;

import biz.neustar.merc.mt.exceptions.MTException;
import biz.neustar.merc.mt.vo.MTRequest;
import biz.neustar.merc.mt.vo.MercResponse;

/**
 * @author pkuninti
 *
 */
public interface MTBusinessService {
	public MercResponse processRequest(MTRequest mtRequest) throws MTException;
	public MercResponse processRequest(MTRequest mtRequest, String mtRequestMapKey) throws MTException;
}
