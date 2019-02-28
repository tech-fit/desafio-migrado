//
//  Enums.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 23/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import Foundation
import UIKit

enum UDKeys: String {
    case theme
    case onboardingComplete
}

//ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue

enum ThemeColorsBackground: String {
    case Light = "lightBackground"
    case Dark = "darkBackground"
    static let allValues = [Light, Dark]
}

enum ThemeColorsButton: String {
    case Light = "lightButton"
    case Dark = "darkButton"
    static let allValues = [Light, Dark]
}

enum Onboarding: String {
    case OnboardingComplete = "onboardingComplete"
    static let allValues = [OnboardingComplete]
}

enum Margin {
    static let horizontal: CGFloat = 24
    static let verticalLarge: CGFloat = 24
    static let verticalVeryLarge: CGFloat = 72
}

class UserDefaultsManager {
    static var defaults = UserDefaults.standard
    
    class func setTheme(to themeNumber: Int) {
        defaults.set(themeNumber, forKey: UDKeys.theme.rawValue)
        synchronize()
    }
    
    class func themeNumber() -> Int {
        return defaults.integer(forKey: UDKeys.theme.rawValue)
    }
    
    class func setOnboardingShown(_ value: Bool) {
        defaults.set(value, forKey: UDKeys.onboardingComplete.rawValue)
        synchronize()
    }
    
    class func isOnboardingShown() -> Bool {
        return defaults.bool(forKey: UDKeys.onboardingComplete.rawValue)
    }
    
    class func synchronize() {
        defaults.synchronize()
    }
}

