package Game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * Stat elmentése XML dokumentumba.
 * 
 * @author skot92
 */
public class XmlSave {

	/**
	 * Alapértelmezett gyár. 
	 */
	private DocumentBuilderFactory dbf;

	/**
	 * Feldolgozó.
	 */
	private DocumentBuilder parser;

	/**
	 * Adatbázis kapcsolat.
	 */
	private JDBC stat;

	/**
	 * Az adott osztály naplózója.
	 * */
	private Logger logger = LoggerFactory.getLogger(XmlSave.class);

	/**
	 * DocumentBuilderFactory,DocumentBuilder és az adatbázis iniciálása.
	 */
	public XmlSave() {
		
		dbf = DocumentBuilderFactory.newInstance();
		try {
			parser = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * Stat elmentése XML fájlba.
	 * 
	 * @throws MySaveException I/O hiba.
	 * @throws MyDBException Adatbázis hiba.
	 */
	public void newXml() throws MyDBException, MySaveException {
		try{
			stat = new JDBC();
		}catch (Exception e) {
			logger.debug("Error connecting to database in newXml");
			throw new MyDBException();
		}
		
		List<PlayerInformations> players = stat.getPlayers();
		Document statDoc = parser.newDocument();
		Element playersElement = statDoc.createElement("players");
		statDoc.appendChild(playersElement);

		for (PlayerInformations player : players) {
			Element playerElement = statDoc.createElement("player");
			playerElement.setAttribute("name", player.getName());
			playersElement.appendChild(playerElement);

			playerElement.appendChild(statDoc.createElement("win"))
					.appendChild(
							statDoc.createTextNode(String.valueOf(player
									.getWins())));

			playerElement.appendChild(statDoc.createElement("loses"))
					.appendChild(
							statDoc.createTextNode(String.valueOf(player
									.getLoos())));

			playerElement.appendChild(statDoc.createElement("tie"))
					.appendChild(
							statDoc.createTextNode(String.valueOf(player
									.getTies())));

		}

		DOMImplementationLS dils = (DOMImplementationLS) parser
				.getDOMImplementation();
		LSSerializer lsser = dils.createLSSerializer();
		LSOutput lsout = dils.createLSOutput();
		try {
			//ISO-8859-2
			lsout.setEncoding("UTF-8");
			lsout.setCharacterStream(new FileWriter("players.xml"));
			lsser.getDomConfig().setParameter("format-pretty-print", true);
			lsser.write(statDoc, lsout);
		} catch (IOException e) {
			logger.error("Failed to save. ");
			throw new MySaveException();
		}
	}
}
