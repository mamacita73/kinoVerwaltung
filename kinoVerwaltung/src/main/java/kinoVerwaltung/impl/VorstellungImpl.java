/**
 */
package kinoVerwaltung.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.Date;

import kinoVerwaltung.*;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vorstellung</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.VorstellungImpl#getFilmTitle <em>Film Title</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.VorstellungImpl#getStartzeit <em>Startzeit</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.VorstellungImpl#getDauerMinuten <em>Dauer Minuten</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.VorstellungImpl#getBuchungen <em>Buchungen</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VorstellungImpl extends MinimalEObjectImpl.Container implements Vorstellung {
	/**
	 * The default value of the '{@link #getFilmTitle() <em>Film Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilmTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String FILM_TITLE_EDEFAULT = null;

	protected EList<Saal> saal;

	/**
	 * The cached value of the '{@link #getFilmTitle() <em>Film Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilmTitle()
	 * @generated
	 * @ordered
	 */
	protected String filmTitle = FILM_TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartzeit() <em>Startzeit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartzeit()
	 * @generated
	 * @ordered
	 */
	protected static final Date STARTZEIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartzeit() <em>Startzeit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartzeit()
	 * @generated
	 * @ordered
	 */
	protected Date startzeit = STARTZEIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDauerMinuten() <em>Dauer Minuten</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDauerMinuten()
	 * @generated
	 * @ordered
	 */
	protected static final int DAUER_MINUTEN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDauerMinuten() <em>Dauer Minuten</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDauerMinuten()
	 * @generated
	 * @ordered
	 */
	protected int dauerMinuten = DAUER_MINUTEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBuchungen() <em>Buchungen</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuchungen()
	 * @generated
	 * @ordered
	 */
	protected EList<Buchung> buchungen;

	protected double preis;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
    public VorstellungImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.VORSTELLUNG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFilmTitle() {
		return filmTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFilmTitle(String newFilmTitle) {
		String oldFilmTitle = filmTitle;
		filmTitle = newFilmTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.VORSTELLUNG__FILM_TITLE,
					oldFilmTitle, filmTitle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getStartzeit() {
		return startzeit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStartzeit(Date newStartzeit) {
		Date oldStartzeit = startzeit;
		startzeit = newStartzeit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.VORSTELLUNG__STARTZEIT,
					oldStartzeit, startzeit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDauerMinuten() {
		return dauerMinuten;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDauerMinuten(int newDauerMinuten) {
		int oldDauerMinuten = dauerMinuten;
		dauerMinuten = newDauerMinuten;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.VORSTELLUNG__DAUER_MINUTEN,
					oldDauerMinuten, dauerMinuten));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Buchung> getBuchungen() {
		if (buchungen == null) {
			buchungen = new EObjectContainmentEList<Buchung>(Buchung.class, this,
					KinoVerwaltungPackage.VORSTELLUNG__BUCHUNGEN);
		}
		return buchungen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double berechneEinnahmen() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return
	 * @generated
	 */
	@Override
	public EList<Sitzplatz> getFreiePlätze() {
		EList<Sitzplatz> freieSitzplätze = new BasicEList<>();

		Saal saal = (Saal) eContainer();
		for (Sitzreihe sitzreihe : saal.getSitzreihen()) {
			for (Sitzplatz sitz : sitzreihe.getSitzplaetze()) {
				if (sitz.getSitzstatus() == Sitzstatus.FREI) {
					freieSitzplätze.add(sitz);
				}
			}
		}
		return freieSitzplätze;
	}

	public EList<Saal> getSaal() {
		return saal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KinoVerwaltungPackage.VORSTELLUNG__BUCHUNGEN:
			return ((InternalEList<?>) getBuchungen()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KinoVerwaltungPackage.VORSTELLUNG__FILM_TITLE:
			return getFilmTitle();
		case KinoVerwaltungPackage.VORSTELLUNG__STARTZEIT:
			return getStartzeit();
		case KinoVerwaltungPackage.VORSTELLUNG__DAUER_MINUTEN:
			return getDauerMinuten();
		case KinoVerwaltungPackage.VORSTELLUNG__BUCHUNGEN:
			return getBuchungen();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case KinoVerwaltungPackage.VORSTELLUNG__FILM_TITLE:
			setFilmTitle((String) newValue);
			return;
		case KinoVerwaltungPackage.VORSTELLUNG__STARTZEIT:
			setStartzeit((Date) newValue);
			return;
		case KinoVerwaltungPackage.VORSTELLUNG__DAUER_MINUTEN:
			setDauerMinuten((Integer) newValue);
			return;
		case KinoVerwaltungPackage.VORSTELLUNG__BUCHUNGEN:
			getBuchungen().clear();
			getBuchungen().addAll((Collection<? extends Buchung>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case KinoVerwaltungPackage.VORSTELLUNG__FILM_TITLE:
			setFilmTitle(FILM_TITLE_EDEFAULT);
			return;
		case KinoVerwaltungPackage.VORSTELLUNG__STARTZEIT:
			setStartzeit(STARTZEIT_EDEFAULT);
			return;
		case KinoVerwaltungPackage.VORSTELLUNG__DAUER_MINUTEN:
			setDauerMinuten(DAUER_MINUTEN_EDEFAULT);
			return;
		case KinoVerwaltungPackage.VORSTELLUNG__BUCHUNGEN:
			getBuchungen().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case KinoVerwaltungPackage.VORSTELLUNG__FILM_TITLE:
			return FILM_TITLE_EDEFAULT == null ? filmTitle != null : !FILM_TITLE_EDEFAULT.equals(filmTitle);
		case KinoVerwaltungPackage.VORSTELLUNG__STARTZEIT:
			return STARTZEIT_EDEFAULT == null ? startzeit != null : !STARTZEIT_EDEFAULT.equals(startzeit);
		case KinoVerwaltungPackage.VORSTELLUNG__DAUER_MINUTEN:
			return dauerMinuten != DAUER_MINUTEN_EDEFAULT;
		case KinoVerwaltungPackage.VORSTELLUNG__BUCHUNGEN:
			return buchungen != null && !buchungen.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case KinoVerwaltungPackage.VORSTELLUNG___BERECHNE_EINNAHMEN:
			return berechneEinnahmen();
		case KinoVerwaltungPackage.VORSTELLUNG___GET_FREIE_PLÄTZE:
			getFreiePlätze();
			return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (filmTitle: ");
		result.append(filmTitle);
		result.append(", startzeit: ");
		result.append(startzeit);
		result.append(", dauerMinuten: ");
		result.append(dauerMinuten);
		result.append(')');
		return result.toString();
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
} //VorstellungImpl
