
package com.myeclipseide.examples.terraws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.terraservice_usa.AreaBoundingBox;
import com.terraservice_usa.ArrayOfPlaceFacts;
import com.terraservice_usa.ArrayOfThemeBoundingBox;
import com.terraservice_usa.LonLatPt;
import com.terraservice_usa.Place;
import com.terraservice_usa.PlaceFacts;
import com.terraservice_usa.PlaceType;
import com.terraservice_usa.Scale;
import com.terraservice_usa.ThemeInfo;
import com.terraservice_usa.TileId;
import com.terraservice_usa.TileMeta;
import com.terraservice_usa.UtmPt;

@WebService(name = "TerraServiceSoap", targetNamespace = "http://terraservice-usa.com/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface TerraServiceSoap {

	@WebMethod(operationName = "ConvertUtmPtToLonLatPt", action = "http://terraservice-usa.com/ConvertUtmPtToLonLatPt")
	@WebResult(name = "ConvertUtmPtToLonLatPtResult", targetNamespace = "http://terraservice-usa.com/")
	public LonLatPt convertUtmPtToLonLatPt(
			@WebParam(name = "utm", targetNamespace = "http://terraservice-usa.com/")
			UtmPt utm);

	@WebMethod(operationName = "CountPlacesInRect", action = "http://terraservice-usa.com/CountPlacesInRect")
	@WebResult(name = "CountPlacesInRectResult", targetNamespace = "http://terraservice-usa.com/")
	public int countPlacesInRect(
			@WebParam(name = "upperleft", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt upperleft,
			@WebParam(name = "lowerright", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt lowerright,
			@WebParam(name = "ptype", targetNamespace = "http://terraservice-usa.com/")
			PlaceType ptype);

	@WebMethod(operationName = "GetTile", action = "http://terraservice-usa.com/GetTile")
	@WebResult(name = "GetTileResult", targetNamespace = "http://terraservice-usa.com/")
	public byte[] getTile(
			@WebParam(name = "id", targetNamespace = "http://terraservice-usa.com/")
			TileId id);

	@WebMethod(operationName = "GetPlaceFacts", action = "http://terraservice-usa.com/GetPlaceFacts")
	@WebResult(name = "GetPlaceFactsResult", targetNamespace = "http://terraservice-usa.com/")
	public PlaceFacts getPlaceFacts(
			@WebParam(name = "place", targetNamespace = "http://terraservice-usa.com/")
			Place place);

	@WebMethod(operationName = "ConvertPlaceToLonLatPt", action = "http://terraservice-usa.com/ConvertPlaceToLonLatPt")
	@WebResult(name = "ConvertPlaceToLonLatPtResult", targetNamespace = "http://terraservice-usa.com/")
	public LonLatPt convertPlaceToLonLatPt(
			@WebParam(name = "place", targetNamespace = "http://terraservice-usa.com/")
			Place place);

	@WebMethod(operationName = "GetTileMetaFromLonLatPt", action = "http://terraservice-usa.com/GetTileMetaFromLonLatPt")
	@WebResult(name = "GetTileMetaFromLonLatPtResult", targetNamespace = "http://terraservice-usa.com/")
	public TileMeta getTileMetaFromLonLatPt(
			@WebParam(name = "point", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt point,
			@WebParam(name = "theme", targetNamespace = "http://terraservice-usa.com/")
			int theme,
			@WebParam(name = "scale", targetNamespace = "http://terraservice-usa.com/")
			Scale scale);

	@WebMethod(operationName = "GetPlaceListInRect", action = "http://terraservice-usa.com/GetPlaceListInRect")
	@WebResult(name = "GetPlaceListInRectResult", targetNamespace = "http://terraservice-usa.com/")
	public ArrayOfPlaceFacts getPlaceListInRect(
			@WebParam(name = "upperleft", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt upperleft,
			@WebParam(name = "lowerright", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt lowerright,
			@WebParam(name = "ptype", targetNamespace = "http://terraservice-usa.com/")
			PlaceType ptype,
			@WebParam(name = "MaxItems", targetNamespace = "http://terraservice-usa.com/")
			int MaxItems);

	@WebMethod(operationName = "GetTheme", action = "http://terraservice-usa.com/GetTheme")
	@WebResult(name = "GetThemeResult", targetNamespace = "http://terraservice-usa.com/")
	public ThemeInfo getTheme(
			@WebParam(name = "theme", targetNamespace = "http://terraservice-usa.com/")
			int theme);

	@WebMethod(operationName = "GetAreaFromTileId", action = "http://terraservice-usa.com/GetAreaFromTileId")
	@WebResult(name = "GetAreaFromTileIdResult", targetNamespace = "http://terraservice-usa.com/")
	public AreaBoundingBox getAreaFromTileId(
			@WebParam(name = "id", targetNamespace = "http://terraservice-usa.com/")
			TileId id,
			@WebParam(name = "displayPixWidth", targetNamespace = "http://terraservice-usa.com/")
			int displayPixWidth,
			@WebParam(name = "displayPixHeight", targetNamespace = "http://terraservice-usa.com/")
			int displayPixHeight);

	@WebMethod(operationName = "GetTileMetaFromTileId", action = "http://terraservice-usa.com/GetTileMetaFromTileId")
	@WebResult(name = "GetTileMetaFromTileIdResult", targetNamespace = "http://terraservice-usa.com/")
	public TileMeta getTileMetaFromTileId(
			@WebParam(name = "id", targetNamespace = "http://terraservice-usa.com/")
			TileId id);

	@WebMethod(operationName = "ConvertLonLatPtToNearestPlace", action = "http://terraservice-usa.com/ConvertLonLatPtToNearestPlace")
	@WebResult(name = "ConvertLonLatPtToNearestPlaceResult", targetNamespace = "http://terraservice-usa.com/")
	public String convertLonLatPtToNearestPlace(
			@WebParam(name = "point", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt point);

	@WebMethod(operationName = "ConvertLonLatPtToUtmPt", action = "http://terraservice-usa.com/ConvertLonLatPtToUtmPt")
	@WebResult(name = "ConvertLonLatPtToUtmPtResult", targetNamespace = "http://terraservice-usa.com/")
	public UtmPt convertLonLatPtToUtmPt(
			@WebParam(name = "point", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt point);

	@WebMethod(operationName = "GetAreaFromRect", action = "http://terraservice-usa.com/GetAreaFromRect")
	@WebResult(name = "GetAreaFromRectResult", targetNamespace = "http://terraservice-usa.com/")
	public AreaBoundingBox getAreaFromRect(
			@WebParam(name = "upperLeft", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt upperLeft,
			@WebParam(name = "lowerRight", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt lowerRight,
			@WebParam(name = "theme", targetNamespace = "http://terraservice-usa.com/")
			int theme,
			@WebParam(name = "scale", targetNamespace = "http://terraservice-usa.com/")
			Scale scale);

	@WebMethod(operationName = "GetPlaceList", action = "http://terraservice-usa.com/GetPlaceList")
	@WebResult(name = "GetPlaceListResult", targetNamespace = "http://terraservice-usa.com/")
	public ArrayOfPlaceFacts getPlaceList(
			@WebParam(name = "placeName", targetNamespace = "http://terraservice-usa.com/")
			String placeName,
			@WebParam(name = "MaxItems", targetNamespace = "http://terraservice-usa.com/")
			int MaxItems,
			@WebParam(name = "imagePresence", targetNamespace = "http://terraservice-usa.com/")
			boolean imagePresence);

	@WebMethod(operationName = "GetAreaFromPt", action = "http://terraservice-usa.com/GetAreaFromPt")
	@WebResult(name = "GetAreaFromPtResult", targetNamespace = "http://terraservice-usa.com/")
	public AreaBoundingBox getAreaFromPt(
			@WebParam(name = "center", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt center,
			@WebParam(name = "theme", targetNamespace = "http://terraservice-usa.com/")
			int theme,
			@WebParam(name = "scale", targetNamespace = "http://terraservice-usa.com/")
			Scale scale,
			@WebParam(name = "displayPixWidth", targetNamespace = "http://terraservice-usa.com/")
			int displayPixWidth,
			@WebParam(name = "displayPixHeight", targetNamespace = "http://terraservice-usa.com/")
			int displayPixHeight);

	@WebMethod(operationName = "GetLatLonMetrics", action = "http://terraservice-usa.com/GetLatLonMetrics")
	@WebResult(name = "GetLatLonMetricsResult", targetNamespace = "http://terraservice-usa.com/")
	public ArrayOfThemeBoundingBox getLatLonMetrics(
			@WebParam(name = "point", targetNamespace = "http://terraservice-usa.com/")
			LonLatPt point);

}
