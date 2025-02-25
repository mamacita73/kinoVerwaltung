/**
 */
package kinoVerwaltung.util;

import kinoVerwaltung.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see kinoVerwaltung.KinoVerwaltungPackage
 * @generated
 */
public class KinoVerwaltungSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static KinoVerwaltungPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KinoVerwaltungSwitch() {
		if (modelPackage == null) {
			modelPackage = KinoVerwaltungPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case KinoVerwaltungPackage.KINO: {
			Kino kino = (Kino) theEObject;
			T result = caseKino(kino);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.SAAL: {
			Saal saal = (Saal) theEObject;
			T result = caseSaal(saal);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.SITZPLATZ: {
			Sitzplatz sitzplatz = (Sitzplatz) theEObject;
			T result = caseSitzplatz(sitzplatz);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.VORSTELLUNG: {
			Vorstellung vorstellung = (Vorstellung) theEObject;
			T result = caseVorstellung(vorstellung);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.BUCHUNG: {
			Buchung buchung = (Buchung) theEObject;
			T result = caseBuchung(buchung);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.BENUTZER: {
			Benutzer benutzer = (Benutzer) theEObject;
			T result = caseBenutzer(benutzer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.RESERVIERUNG: {
			Reservierung reservierung = (Reservierung) theEObject;
			T result = caseReservierung(reservierung);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case KinoVerwaltungPackage.SITZREIHE: {
			Sitzreihe sitzreihe = (Sitzreihe) theEObject;
			T result = caseSitzreihe(sitzreihe);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Kino</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Kino</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKino(Kino object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Saal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Saal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSaal(Saal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sitzplatz</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sitzplatz</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSitzplatz(Sitzplatz object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vorstellung</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vorstellung</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVorstellung(Vorstellung object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Buchung</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Buchung</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuchung(Buchung object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Benutzer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Benutzer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBenutzer(Benutzer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reservierung</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reservierung</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReservierung(Reservierung object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sitzreihe</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sitzreihe</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSitzreihe(Sitzreihe object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //KinoVerwaltungSwitch
